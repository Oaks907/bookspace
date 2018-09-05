package com.wisely.ch9_4_integration;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

import static java.lang.System.getProperty;


@SpringBootApplication
public class Ch94IntegrationApplication {

  public static void main(String[] args) {
    SpringApplication.run(Ch94IntegrationApplication.class, args);
  }


  @Value(value = "https://spring.io/blog.atom")
  Resource resource;

  //配置轮训方式
  @Bean(name = PollerMetadata.DEFAULT_POLLER)
  public PollerMetadata poller() {
    return Pollers.fixedRate(500).get();
  }

  @Bean
  public FeedEntryMessageSource feedEntryMessageSource() throws Exception {
    FeedEntryMessageSource messageSource = new FeedEntryMessageSource(resource.getURL(), "news");
    return messageSource;
  }

  @Bean
  public IntegrationFlow myFlow() throws Exception {
    return IntegrationFlows.from(feedEntryMessageSource()).
      <SyndEntry, String>route(payload -> payload.getCategories().get(0).getName(),
        mapping -> mapping.channelMapping("releases", "releasesChannel")
          .channelMapping("engineering", "engineeringChannel")).get();
  }

  @Bean
  public IntegrationFlow releaseFlow() {
    return IntegrationFlows.from(MessageChannels.queue("releasesChannel", 10))
      .<SyndEntry, String>transform(payload -> "《" + payload.getTitle() + "》" + payload.getLink()
        + getProperty("line.separator"))
      .handle(Files.outboundAdapter(new File("/Users/haifei/Downloads"))
        .fileExistsMode(FileExistsMode.APPEND)
        .charset("UTF-8")
        .fileNameGenerator(message -> "release.txt")
        .get()).get();
  }

  @Bean
  public IntegrationFlow engineeringFlow() {
    return IntegrationFlows.from(MessageChannels.queue("engineeringChannel", 10))
      .<SyndEntry, String> transform(
        payload -> "《" + payload.getTitle() + "》 " + payload.getLink() + getProperty("line.separator"))
      .handle(Files.outboundAdapter(new File("/Users/haifei/Downloads"))
        .fileExistsMode(FileExistsMode.APPEND)
        .charset("UTF-8")
        .fileNameGenerator(message -> "engineering.txt")
        .get())
      .get();
  }


}
