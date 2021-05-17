package com.mati.github.services;

import com.mati.github.model.GitHubUserDataResponse;
import com.mati.github.model.UserDataExtended;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest
class GitHubServiceTest {

    @Autowired
    private GitHubService gitHubService;

    @MockBean
    private UserStatsService userStatsServiceMock;

    @MockBean
    private GitHubUserGrabbingService gitHubUserGrabbingServiceMock;


    @SneakyThrows
    @Test
    public void shouldFetchDataFromSiteAndUpdateStats() {
        // given
        doNothing().when(userStatsServiceMock).updateStats("login");

        GitHubUserDataResponse gitHubUserDataResponse = new GitHubUserDataResponse();
        gitHubUserDataResponse.setLogin("login");
        when(gitHubUserGrabbingServiceMock.getGitHubUserDataResponse("login"))
                .thenReturn(gitHubUserDataResponse);

        // when
        UserDataExtended result = gitHubService.getUserData("login");

        // then
        UserDataExtended expectedResult = new UserDataExtended();
        expectedResult.setLogin("login");
        Assertions.assertEquals(expectedResult, result);
        Mockito.verify(userStatsServiceMock).updateStats("login");
    }

    @SneakyThrows
    @Test
    public void shouldReturnEmptyDataFromSiteWhenException() {
        // given
        GitHubUserDataResponse gitHubUserDataResponse = new GitHubUserDataResponse();
        gitHubUserDataResponse.setLogin("login");
        when(gitHubUserGrabbingServiceMock.getGitHubUserDataResponse("login"))
                .thenThrow(new InterruptedException());

        // when
        UserDataExtended result = gitHubService.getUserData("login");

        // then
        Assertions.assertEquals(new UserDataExtended(), result);
        Mockito.verifyNoMoreInteractions(userStatsServiceMock);
    }

}