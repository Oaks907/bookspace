package com.wisly.ch9_3_5_amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Ch935AmqpApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Ch935AmqpApplication.class, args);
	}

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Bean
	public Queue wiselyQueue() {
		return new Queue("my-queue");
	}


	@Override
	public void run(String... args) throws Exception {
		rabbitTemplate.convertAndSend("my-queue", "来自与RabbitMQ的消息");
	}
}
