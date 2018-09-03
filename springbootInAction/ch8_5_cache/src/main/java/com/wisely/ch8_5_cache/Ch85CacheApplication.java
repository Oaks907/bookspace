package com.wisely.ch8_5_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Ch85CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ch85CacheApplication.class, args);
	}
}
