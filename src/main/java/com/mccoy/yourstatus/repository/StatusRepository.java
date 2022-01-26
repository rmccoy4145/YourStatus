package com.mccoy.yourstatus.repository;

import com.mccoy.yourstatus.entity.Follower;
import com.mccoy.yourstatus.entity.Status;
import com.mccoy.yourstatus.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class StatusRepository {
    @PersistenceContext
    EntityManager em;

    List<Status> getStatusByUser(User user){
        return em.createQuery("SELECT f FROM Status f WHERE f.user = :user", Status.class)
                .setParameter("user", user)
                .getResultList();
    }
}
