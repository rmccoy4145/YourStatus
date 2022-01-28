package com.mccoy.yourstatus.service;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.repository.Repository;
import com.mccoy.yourstatus.repository.impl.UserRepositoryImpl;

import javax.enterprise.context.ApplicationScoped;

/**
 * Handles operations around the User entity
 */
@ApplicationScoped
public class UserService {
    Repository<User> repo = new UserRepositoryImpl();

    /**
     * Add new user
     * @param user
     * @return
     */
    public User addUser(User user) {
        return repo.add(user);
    }

    /**
     * Get User
     * @param id
     * @return
     */
    public User getUser(Long id) {
        return repo.get(id);
    }

}
