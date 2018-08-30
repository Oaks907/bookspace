package com.wisely.ch7_4_tomcatcofigure;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ch74TomcatcofigureApplication {

  public static void main(String[] args) {
    SpringApplication.run(Ch74TomcatcofigureApplication.class, args);
  }

  //这里配置必须使用static
//  @Component
//  public static class CustomServletContainer implements EmbeddedServletContainerCustomizer {
//
//    @Override
//    public void customize(ConfigurableEmbeddedServletContainer container) {
//      container.setPort(8888);//1
//      container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
//      container.setSessionTimeout(10, TimeUnit.MINUTES);
//    }
//  }

  @Bean
  public EmbeddedServletContainerFactory servletContainer() {
    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
      @Override
      protected void postProcessContext(Context context) {
        SecurityConstraint securityConstraint = new SecurityConstraint();
        securityConstraint.setUserConstraint("CONFIDENTIAL");
        SecurityCollection collection = new SecurityCollection();
        collection.addPattern("/*");
        securityConstraint.addCollection(collection);
        context.addConstraint(securityConstraint);
      }
    };

    tomcat.addAdditionalTomcatConnectors(httpConnector());
    return tomcat;
  }

  @Bean
  public Connector httpConnector() {
    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    connector.setScheme("http");
    connector.setPort(8080);
    connector.setSecure(false);
    connector.setRedirectPort(8443);
    return connector;
  }
}
