package com.mccoy.yourstatus.repository.impl;

import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.repository.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserFollowRepositoryImpl implements Repository<UserFollow> {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<UserFollow> getAllByUser(User user){
       return em.createQuery("SELECT f FROM UserFollow f WHERE f.user = :user", UserFollow.class)
               .setParameter("user", user)
               .getResultList();
    }

    public List<UserFollow> getFollowersBy(User user){
        return em.createQuery("SELECT f FROM UserFollow f WHERE f.followUser = :user", com.mccoy.yourstatus.entity.UserFollow.class)
                .setParameter("user", user)
                .getResultList();
    }


    @Override
    public UserFollow get(Long id) {
        return em.find(UserFollow.class, id);
    }

    @Override
    public List<UserFollow> getAll() {
        return em.createQuery("SELECT f FROM UserFollow f", UserFollow.class)
                .getResultList();
    }

    @Override
    public UserFollow add(UserFollow userFollow) {
        em.persist(userFollow);
        return userFollow;
    }

    @Override
    public UserFollow update(UserFollow userFollow) {
        em.merge(userFollow);
        return userFollow;
    }

    @Override
    public UserFollow remove(UserFollow userFollow) {
        em.remove(userFollow);
        return userFollow;
    }
}
