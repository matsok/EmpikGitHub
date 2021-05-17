package com.mati.github.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOGIN_STATS")
public class LoginStats {
    @Id
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "REQUEST_COUNT")
    private Integer requestCount;

    public LoginStats incrementStats() {
        if(this.requestCount != null) this.requestCount++;
        return this;
    }
}
