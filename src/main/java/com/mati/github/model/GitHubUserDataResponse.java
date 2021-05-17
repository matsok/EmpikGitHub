package com.mati.github.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUserDataResponse {
    private Integer id;
    private String login;
    private String name;
    private String type;
    @JsonAlias("avatar_url")
    private String avatarUrl;
    @JsonAlias("node_id")
    private String nodeId;
    @JsonAlias("gravatar_id")
    private String gravatarId;
    private String url;
    @JsonAlias("html_url")
    private String htmlUrl;
    @JsonAlias("followers_url")
    private String followersUrl;
    @JsonAlias("following_url")
    private String followingUrl;
    @JsonAlias("gists_url")
    private String gistsUrl;
    @JsonAlias("starred_url")
    private String starredUrl;
    @JsonAlias("subscriptions_url")
    private String subscriptionsUrl;
    @JsonAlias("organizations_url")
    private String organizationsUrl;
    @JsonAlias("repos_url")
    private String reposUrl;
    @JsonAlias("events_url")
    private String eventsUrl;
    @JsonAlias("received_events_url")
    private String receivedEventsUrl;
    @JsonAlias("site_admin")
    private Boolean siteAdmin;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String hireable;
    private String bio;
    @JsonAlias("twitter_username")
    private String twitterUsername;
    @JsonAlias("public_repos")
    private Integer publicRepos;
    @JsonAlias("public_gists")
    private Integer publicGists;
    private Integer followers;
    private Integer following;
    @JsonAlias("created_at")
    private LocalDateTime createdAt;
    @JsonAlias("updated_at")
    private LocalDateTime updatedAt;

    public UserDataExtended mapToExtended() {
        UserDataExtended userDataExtended = new UserDataExtended();
        userDataExtended.setId(this.id);
        userDataExtended.setLogin(this.login);
        userDataExtended.setName(this.name);
        userDataExtended.setType(this.type);
        userDataExtended.setAvatarUrl(this.avatarUrl);
        userDataExtended.setCreatedAt(this.createdAt);
        userDataExtended.setCalculations(calculate());

        return userDataExtended;
    }

    private Double calculate() {
        if(this.followers == null || this.followers == 0 || this.publicRepos == null) return null;

        return (double) 6 / this.getFollowers() * 2 * this.getPublicRepos();
    }
}
