package com.mati.github.services;

import com.mati.github.model.LoginStats;
import com.mati.github.repository.UserStatsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserStatsServiceTest {

    @Autowired
    private UserStatsService userStatsService;

    @MockBean
    private UserStatsRepository userStatsRepositoryMock;

    @Test
    public void shouldUpdateExistingUserStatByIncrementingCounter() {
        // given
        when(userStatsRepositoryMock.existsById("login")).thenReturn(true);
        LoginStats loginStats = new LoginStats("login", 4);
        when(userStatsRepositoryMock.getOne("login")).thenReturn(loginStats);
        LoginStats resultingLoginStats = loginStats.incrementStats();
        when(userStatsRepositoryMock.save(resultingLoginStats)).thenReturn(resultingLoginStats);

        // when
        userStatsService.updateStats("login");

        // then
        verify(userStatsRepositoryMock).save(resultingLoginStats);
        verify(userStatsRepositoryMock, never()).save(new LoginStats("login", 1));
    }

    @Test
    public void shouldPersistNewUserStatWithCounterEqualsOne() {
        // given
        when(userStatsRepositoryMock.existsById("login")).thenReturn(false);
        LoginStats loginStats = new LoginStats("login", 1);
        when(userStatsRepositoryMock.save(loginStats)).thenReturn(loginStats);

        // when
        userStatsService.updateStats("login");

        // then
        ArgumentCaptor<LoginStats> argument = ArgumentCaptor.forClass(LoginStats.class);
        verify(userStatsRepositoryMock).save(argument.capture());
        assertEquals("login", argument.getValue().getLogin());
    }
}