package com.mccoy.yourstatus.service;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.invalid.InvalidUser;
import com.mccoy.yourstatus.repository.impl.UserDAOImpl;

import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

/**
 * Handles operations around the User entity
 */

public class UserService {

    Logger LOG = Logger.getLogger(UserService.class.getName());

    UserDAOImpl dao;

    @Inject
    public UserService(UserDAOImpl dao) {
        this.dao = dao;
    }

    /**
     * Add new user
     * @param user
     * @return
     */
    public User addUser(User user) {
        return dao.add(user);
    }

    /**
     * Get User
     * @param id
     * @return
     */
    public User getUser(Long id) {
        return dao.get(id).orElseGet(InvalidUser::new);
    }

    public List<User> getAllUsers() {
        return dao.getAll();
    }


}
