package com.mccoy.yourstatus.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_status_message")
public class UserStatusMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "datetime", nullable = false)
    //TODO: date time format does not match postman mock server
    private LocalDateTime datetime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonbTransient
    private User user;

    @Transient
    private Long userId;

    public Long getUserId() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.userId = user.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime timestamp) {
        this.datetime = timestamp;
    }
}