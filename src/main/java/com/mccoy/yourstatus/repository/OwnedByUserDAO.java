package com.mccoy.yourstatus.repository;

import com.mccoy.yourstatus.entity.User;

import java.util.List;

public interface OwnedByUserDAO<T> {
    List<T> getAllByUser(User user);
}
