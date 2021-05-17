package com.mati.github.services;

import com.mati.github.model.UserStats;
import com.mati.github.repository.UserStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStatsService {

    @Autowired
    private UserStatsRepository userStatsRepository;

    public void updateStats(String login) {
        boolean exists = userStatsRepository.existsById(login);
        if(exists) {
            UserStats userStats = userStatsRepository.getOne(login);
            userStatsRepository.save(userStats.incrementStats());
            return;
        }

        userStatsRepository.save(new UserStats(login, 1));
    }
}
