package com.mccoy.yourstatus.service;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.repository.Repository;
import com.mccoy.yourstatus.repository.impl.UserRepositoryImpl;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

/**
 * Handles operations around the User entity
 */

public class UserService {

    Logger LOG = Logger.getLogger(UserService.class.getName());
    @Inject
    UserRepositoryImpl repo;

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

    public List<User> getAllUsers() {
        return repo.getAll();
    }


}
