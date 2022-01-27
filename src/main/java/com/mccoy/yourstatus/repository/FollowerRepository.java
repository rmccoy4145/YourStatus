package com.mccoy.yourstatus.repository;

import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class FollowerRepository {
    @PersistenceContext
    EntityManager em;

    List<UserFollow> getFollowersByUser(User user){
       return em.createQuery("SELECT f FROM UserFollow f WHERE f.user = :user", UserFollow.class)
               .setParameter("user", user)
               .getResultList();
    }

}
