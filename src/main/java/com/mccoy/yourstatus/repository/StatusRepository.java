package com.mccoy.yourstatus.repository;

import com.mccoy.yourstatus.entity.UserStatusMessage;
import com.mccoy.yourstatus.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class StatusRepository {
    @PersistenceContext
    EntityManager em;

    List<UserStatusMessage> getStatusByUser(User user){
        return em.createQuery("SELECT f FROM UserStatusMessage f WHERE f.user = :user", UserStatusMessage.class)
                .setParameter("user", user)
                .getResultList();
    }
}
