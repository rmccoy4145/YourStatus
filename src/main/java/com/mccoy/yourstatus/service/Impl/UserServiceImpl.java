package com.mccoy.yourstatus.service.Impl;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.service.AbstractJpaService;
import com.mccoy.yourstatus.service.GenericDAO;

import javax.ejb.Local;
import javax.ejb.Stateful;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Handles operations around the User entity
 */
@Stateful
@Local
public class UserServiceImpl extends AbstractJpaService<User> implements GenericDAO<User> {

    Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());

    public UserServiceImpl() {
    }

    @Override
    public Class<User> getType() {
        return User.class;
    }


}
