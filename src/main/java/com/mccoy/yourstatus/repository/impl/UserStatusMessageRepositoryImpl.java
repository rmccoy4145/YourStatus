package com.mccoy.yourstatus.repository.impl;

import com.mccoy.yourstatus.entity.UserStatusMessage;
import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.repository.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserStatusMessageRepositoryImpl implements Repository<UserStatusMessage> {
    @PersistenceContext
    EntityManager em;

    @Override
    public UserStatusMessage get(Long id) {
        return em.find(UserStatusMessage.class, id);
    }

    @Override
    public List<UserStatusMessage> getAll() {
        return em.createQuery("SELECT f FROM UserStatusMessage f", UserStatusMessage.class)
                .getResultList();
    }

    @Override
    public UserStatusMessage add(UserStatusMessage userStatusMessage) {
        em.persist(userStatusMessage);
        return userStatusMessage;
    }

    @Override
    public UserStatusMessage update(UserStatusMessage userStatusMessage) {
        return em.merge(userStatusMessage);
    }

    @Override
    public UserStatusMessage remove(UserStatusMessage userStatusMessage) {
        em.remove(userStatusMessage);
        return userStatusMessage;
    }

    @Override
    public List<UserStatusMessage> getAllByUser(User user){
        return em.createQuery("SELECT f FROM UserStatusMessage f WHERE f.user = :user", UserStatusMessage.class)
                .setParameter("user", user)
                .getResultList();
    }
}
