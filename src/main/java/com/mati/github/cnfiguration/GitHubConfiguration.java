package com.mati.github.cnfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class GitHubConfiguration {

    @Bean
    public HttpClient gitHubClient() {
        return HttpClient.newHttpClient();
    }
}
