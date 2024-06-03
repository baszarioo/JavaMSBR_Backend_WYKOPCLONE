package com.b1lbudinhox.wykopclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WykopcloneApplication {
	public static void main(String[] args) {

		System.out.println(org.hibernate.Version.getVersionString());
		SpringApplication.run(WykopcloneApplication.class, args);
	}

}
