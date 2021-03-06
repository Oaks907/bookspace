package com.wisely.ch5_2_2_simplespring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Ch522SimplespringApplication {

	@Value("${book.author}")
	private String bookAuthor;
	@Value("${book.name}")
	private String bookName;

	@RequestMapping("/")
	String index() {
		return "book name is:" + bookName + ", and book author is " + bookName;
	}

	public static void main(String[] args) {
		SpringApplication.run(Ch522SimplespringApplication.class, args);
	}
}
