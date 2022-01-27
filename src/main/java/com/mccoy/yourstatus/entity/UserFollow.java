package com.mccoy.yourstatus.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
@Table(name = "user_follow")
public class UserFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonbTransient
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "follow_user_id", nullable = false)
    @JsonbTransient
    private User followUser;

    @Transient
    private Long userId;

    @Transient
    private Long followUserId;

    public Long getFollowUserId() {
        return followUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.userId = user.getId();
    }

    public User getFollowUser() {
        return followUser;
    }

    public void setFollowUser(User followUser) {
        this.followUser = followUser;
        this.followUserId = followUser.getId();
    }
}