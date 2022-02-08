package com.mccoy.yourstatus.repository.impl;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.repository.AbstractDAO;
import com.mccoy.yourstatus.repository.OwnedByUserDAO;

import javax.transaction.*;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends AbstractDAO<User> {

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(getEm().find(User.class, id));
    }

    @Override
    public List<User> getAll() {
        return getEm().createQuery("SELECT f FROM User f", User.class)
                .getResultList();
    }

    @Override
    public User add(User user) {
        getEm().persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        return getEm().merge(user);
    }

    @Override
    public User remove(User user) {
        getEm().remove(user);
        return user;
    }

}
