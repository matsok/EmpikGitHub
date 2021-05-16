package com.mati.github.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mati.github.model.GitHubUserDataResponse;
import com.mati.github.model.UserDataExtended;
import com.mati.github.properties.GitHubProperties;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GitHubService {

    @Autowired
    private GitHubProperties gitHubProperties;

    @Autowired
    private HttpClient gitHubClient;

    public UserDataExtended getUserData(String login) {

        System.out.println("TEST");
        System.out.println(gitHubProperties.getUrl());

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(gitHubProperties.getUrl() + login))
                .build();

        HttpResponse<String> response = null;
        try {
            response = gitHubClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            System.out.println(response.body());
            val userDataResponse = objectMapper.readValue(response.body(), GitHubUserDataResponse.class);

            return userDataResponse.mapToExtended();
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            return new UserDataExtended();
        }
    }
}
