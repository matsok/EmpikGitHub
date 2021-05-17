package com.mati.github.services;

import com.mati.github.model.UserStats;
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
        UserStats userStats = new UserStats("login", 4);
        when(userStatsRepositoryMock.getOne("login")).thenReturn(userStats);
        UserStats resultingUserStats = userStats.incrementStats();
        when(userStatsRepositoryMock.save(resultingUserStats)).thenReturn(resultingUserStats);

        // when
        userStatsService.updateStats("login");

        // then
        verify(userStatsRepositoryMock).save(resultingUserStats);
        verify(userStatsRepositoryMock, never()).save(new UserStats("login", 1));
    }

    @Test
    public void shouldPersistNewUserStatWithCounterEqualsOne() {
        // given
        when(userStatsRepositoryMock.existsById("login")).thenReturn(false);
        UserStats userStats = new UserStats("login", 1);
        when(userStatsRepositoryMock.save(userStats)).thenReturn(userStats);

        // when
        userStatsService.updateStats("login");

        // then
        ArgumentCaptor<UserStats> argument = ArgumentCaptor.forClass(UserStats.class);
        verify(userStatsRepositoryMock).save(argument.capture());
        assertEquals("login", argument.getValue().getLogin());
    }
}