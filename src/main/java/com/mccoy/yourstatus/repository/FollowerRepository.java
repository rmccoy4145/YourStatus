package com.mccoy.yourstatus.repository;

import com.mccoy.yourstatus.entity.Follower;
import com.mccoy.yourstatus.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class FollowerRepository {
    @PersistenceContext
    EntityManager em;

    List<Follower> getFollowersByUser(User user){
       return em.createQuery("SELECT f FROM Follower f WHERE f.user = :user", Follower.class)
               .setParameter("user", user)
               .getResultList();
    }

}
