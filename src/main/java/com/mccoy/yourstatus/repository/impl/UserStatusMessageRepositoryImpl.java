package com.mccoy.yourstatus.repository.impl;

import com.mccoy.yourstatus.entity.UserStatusMessage;
import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.repository.AbstractDAO;
import com.mccoy.yourstatus.repository.Repository;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class UserStatusMessageRepositoryImpl extends AbstractDAO implements Repository<UserStatusMessage> {

    @Override
    public UserStatusMessage get(Long id) {
        return getEm().find(UserStatusMessage.class, id);
    }

    @Override
    public List<UserStatusMessage> getAll() {
        return getEm().createQuery("SELECT f FROM UserStatusMessage f", UserStatusMessage.class)
                .getResultList();
    }

    public List<UserStatusMessage> getAllBroadcast() {
        return getEm().createQuery("SELECT f FROM UserStatusMessage f WHERE f.broadcast = true", UserStatusMessage.class)
                .getResultList();
    }

    @Override
    public UserStatusMessage add(UserStatusMessage userStatusMessage) {
        getEm().persist(userStatusMessage);
        return userStatusMessage;
    }

    @Override
    public UserStatusMessage update(UserStatusMessage userStatusMessage) {
        return getEm().merge(userStatusMessage);
    }

    @Override
    public UserStatusMessage remove(UserStatusMessage userStatusMessage) {
        remove(userStatusMessage);
        return userStatusMessage;
    }

    @Override
    public List<UserStatusMessage> getAllByUser(User user){
        return getEm().createQuery("SELECT f FROM UserStatusMessage f WHERE f.user = :user", UserStatusMessage.class)
                .setParameter("user", user)
                .getResultList();
    }


}
