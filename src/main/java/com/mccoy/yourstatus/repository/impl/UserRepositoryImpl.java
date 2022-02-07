package com.mccoy.yourstatus.repository.impl;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserStatusMessage;
import com.mccoy.yourstatus.repository.AbstractDAO;
import com.mccoy.yourstatus.repository.Repository;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.List;

public class UserRepositoryImpl extends AbstractDAO implements Repository<User> {


    @Override
    public User get(Long id) {
        User user = null;
        try {
            getUtx().begin();
            user = getEm().find(User.class, id);
            getUtx().commit();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }
        return user;
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

    @Override
    public List<User> getAllByUser(User user) {
        throw new UnsupportedOperationException();
    }
}
