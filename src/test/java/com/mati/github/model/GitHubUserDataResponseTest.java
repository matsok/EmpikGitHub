package com.mati.github.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class GitHubUserDataResponseTest {

    @Test
    public void shouldMapValuesProperly() {
        // given
        GitHubUserDataResponse gitHubUserDataRespone = new GitHubUserDataResponse();
        gitHubUserDataRespone.setId(7);
        gitHubUserDataRespone.setLogin("abc");
        gitHubUserDataRespone.setName("somename");
        gitHubUserDataRespone.setType("User");
        gitHubUserDataRespone.setAvatarUrl("avatar_url");
        gitHubUserDataRespone.setFollowers(3);
        gitHubUserDataRespone.setPublicRepos(10);
        gitHubUserDataRespone.setCreatedAt(LocalDateTime.of(2020, 10, 12, 8, 50, 6));

        // when
        UserDataExtended result = gitHubUserDataRespone.mapToExtended();

        // then
        Assertions.assertEquals(7, result.getId());
        Assertions.assertEquals("abc", result.getLogin());
        Assertions.assertEquals("somename", result.getName());
        Assertions.assertEquals("User", result.getType());
        Assertions.assertEquals("avatar_url", result.getAvatarUrl());
        Assertions.assertEquals(LocalDateTime.of(2020, 10, 12, 8, 50, 6), result.getCreatedAt());
        Assertions.assertEquals(40, result.getCalculations());
    }

    @Test
    public void shouldHandleZeroFollowersInCalculations() {
        // given
        GitHubUserDataResponse gitHubUserDataRespone = new GitHubUserDataResponse();
        gitHubUserDataRespone.setFollowers(0);
        gitHubUserDataRespone.setPublicRepos(10);

        // when
        UserDataExtended result = gitHubUserDataRespone.mapToExtended();

        // then
        Assertions.assertNull(result.getCalculations());
    }

    @Test
    public void shouldHandleNullFollowersInCalculations() {
        // given
        GitHubUserDataResponse gitHubUserDataRespone = new GitHubUserDataResponse();
        // followers set to null by default
        gitHubUserDataRespone.setPublicRepos(10);

        // when
        UserDataExtended result = gitHubUserDataRespone.mapToExtended();

        // then
        Assertions.assertNull(result.getCalculations());
    }

    @Test
    public void shouldHandleNullPublicReposInCalculations() {
        // given
        GitHubUserDataResponse gitHubUserDataRespone = new GitHubUserDataResponse();
        gitHubUserDataRespone.setFollowers(3);
        // public repos set to null by default

        // when
        UserDataExtended result = gitHubUserDataRespone.mapToExtended();

        // then
        Assertions.assertNull(result.getCalculations());
    }

}