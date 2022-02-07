package com.mccoy.yourstatus.repository;

import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.logging.Logger;

public class AbstractDAO {
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

    protected UserTransaction getUtx() {
        return utx;
    }
}
