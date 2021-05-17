package com.mati.github.services;

import com.mati.github.model.GitHubUserDataResponse;
import com.mati.github.model.UserDataExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitHubService {

    @Autowired
    private GitHubUserGrabbingService gitHubUserGrabbingService;

    @Autowired
    private UserStatsService userStatsService;

    public UserDataExtended getUserData(String login) {

        try {
            GitHubUserDataResponse userDataResponse = gitHubUserGrabbingService.getGitHubUserDataResponse(login);
            userStatsService.updateStats(login);

            return userDataResponse.mapToExtended();
        } catch (Exception e) {
            return new UserDataExtended();
        }
    }
}
