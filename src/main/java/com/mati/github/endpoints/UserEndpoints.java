package com.mati.github.endpoints;

import com.mati.github.model.UserDataExtended;
import com.mati.github.services.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEndpoints {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("users/{login}")
    public UserDataExtended getUserData(@PathVariable String login) {

        return gitHubService.getUserData(login);
    }
}
