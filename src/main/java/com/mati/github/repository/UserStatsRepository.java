package com.mati.github.repository;


import com.mati.github.model.LoginStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatsRepository extends JpaRepository<LoginStats, String> {
}
