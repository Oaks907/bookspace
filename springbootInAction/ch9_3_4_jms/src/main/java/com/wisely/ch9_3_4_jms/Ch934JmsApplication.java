package com.wisely.ch9_3_4_jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class Ch934JmsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Ch934JmsApplication.class, args);
	}

	@Autowired
	JmsTemplate jmsTemplate;

	@Override
	public void run(String... args) throws Exception {
		jmsTemplate.send("my-destination", new Msg());
	}
}
