package com.mccoy.yourstatus.service;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.repository.Repository;
import com.mccoy.yourstatus.repository.impl.UserRepositoryImpl;

/**
 * Handles operations around the User entity
 */
public class UserService {
    Repository<User> repo = new UserRepositoryImpl();

    public User addUser(User user) {
        return repo.add(user);
    }

    public User getUser(Long id) {
        return repo.get(id);
    }

}
