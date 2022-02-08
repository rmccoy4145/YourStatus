package com.mccoy.yourstatus.repository;

import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Logger;

public abstract class AbstractDAO<T> {
    Logger LOG = Logger.getLogger(AbstractDAO.class.getName());

    @PersistenceUnit(unitName = "YourStatus_PU")
    private EntityManagerFactory emf;

    EntityManager em;

    @Resource
    UserTransaction utx;

    @PostConstruct
    void init() {
        if (emf == null) {
            LOG.warning("Entity Manager is NULL!!!");
        }
    }

    protected EntityManager getEm() {
        em = emf.createEntityManager();
        if(em == null) {
            LOG.warning("EntityManager is null!!");
        }
        return em;
    }

    protected void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

    protected UserTransaction getUtx() {
        return utx;
    }

    public abstract Optional<T> get(Long id);
    public abstract List<T> getAll();
    public abstract T add(T t);
    public abstract T update(T t);
    public abstract T remove(T t);
}
