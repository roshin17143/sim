package com.project.group16.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Group16ProjectConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Group16ProjectConfigServerApplication.class, args);
	}
}
