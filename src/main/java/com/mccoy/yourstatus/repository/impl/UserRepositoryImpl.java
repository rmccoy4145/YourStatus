package com.mccoy.yourstatus.repository.impl;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserStatusMessage;
import com.mccoy.yourstatus.repository.Repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserRepositoryImpl implements Repository<User> {
    @PersistenceContext(unitName = "YourStatus_PU")
    EntityManager em;

    @Override
    public User get(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT f FROM User f", User.class)
                .getResultList();
    }

    @Override
    public User add(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public User remove(User user) {
        em.remove(user);
        return user;
    }

    @Override
    public List<User> getAllByUser(User user) {
        throw new UnsupportedOperationException();
    }
}
