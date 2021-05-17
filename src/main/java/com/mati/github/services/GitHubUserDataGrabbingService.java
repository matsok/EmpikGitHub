package com.mati.github.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mati.github.model.GitHubUserDataResponse;
import com.mati.github.properties.GitHubProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class GitHubUserDataGrabbingService {

    @Autowired
    private GitHubProperties gitHubProperties;

    @Autowired
    private HttpClient gitHubClient;

    public GitHubUserDataResponse getGitHubUserDataResponse(String login) throws java.io.IOException, InterruptedException {
        HttpRequest request = getHttpRequest(login);

        HttpResponse<String> response = gitHubClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = getObjectMapper();

        return objectMapper.readValue(response.body(), GitHubUserDataResponse.class);
    }

    private HttpRequest getHttpRequest(String login) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(gitHubProperties.getUrl() + login))
                .timeout(gitHubProperties.getTimeout())
                .build();

        return request;
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }
}
