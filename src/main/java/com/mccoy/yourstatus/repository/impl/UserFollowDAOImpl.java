package com.mccoy.yourstatus.repository.impl;

import com.mccoy.yourstatus.entity.UserFollow;
import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.repository.AbstractDAO;
import com.mccoy.yourstatus.repository.OwnedByUserDAO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Transactional
public class UserFollowDAOImpl extends AbstractDAO<UserFollow> implements OwnedByUserDAO<UserFollow> {
    Logger LOG = Logger.getLogger(UserFollowDAOImpl.class.getName());


    @Override
    public List<UserFollow> getAllByUser(User user){
       return getEm().createQuery("SELECT f FROM UserFollow f WHERE f.user = :user", UserFollow.class)
               .setParameter("user", user)
               .getResultList();
    }


    public List<UserFollow> getFollowersBy(User user){
        return getEm().createQuery("SELECT f FROM UserFollow f WHERE f.followUser = :user", com.mccoy.yourstatus.entity.UserFollow.class)
                .setParameter("user", user)
                .getResultList();
    }


    @Override
    public Optional<UserFollow> get(Long id) {
        return Optional.ofNullable(getEm().find(UserFollow.class, id));
    }

    @Override
    public List<UserFollow> getAll() {
        return getEm().createQuery("SELECT f FROM UserFollow f", UserFollow.class)
                .getResultList();
    }

    @Override
    public UserFollow add(UserFollow userFollow) {
        getEm().persist(userFollow);
        return userFollow;
    }

    @Override
    public UserFollow update(UserFollow userFollow) {
        getEm().merge(userFollow);
        return userFollow;
    }

    @Override
    public UserFollow remove(UserFollow userFollow) {
        getEm().remove(userFollow);
        return userFollow;
    }
}
