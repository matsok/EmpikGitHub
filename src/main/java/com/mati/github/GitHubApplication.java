package com.mati.github;

import com.mati.github.properties.GitHubProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(GitHubProperties.class)
@SpringBootApplication
public class GitHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitHubApplication.class, args);
	}

}
