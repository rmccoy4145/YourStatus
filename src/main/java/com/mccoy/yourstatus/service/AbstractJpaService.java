package com.mccoy.yourstatus.service;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Logger;

public abstract class AbstractJpaService<T> implements GenericDAO<T>{
    Logger LOG = Logger.getLogger(AbstractJpaService.class.getName());

    @PersistenceUnit(unitName = "YourStatus_PU")
    private EntityManagerFactory emf;

    protected EntityManager em;


    abstract protected Class<T> getType();

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


    public T get(Long id) throws EntityNotFoundException {
        Optional<T> opt =  Optional.ofNullable(getEm().find(getType(), id));
        if (opt.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return opt.get();
    }

    public List<T> getAll() {

        return getEm().createQuery(new StringBuilder().append("SELECT f FROM ").append(getType().getSimpleName()).append(" f").toString(), getType())
                .getResultList();
    }

    public T add(T t) {
        getEm().persist(t);
        return t;
    }

    public T update(T t) {
        return getEm().merge(t);
    }

    public T remove(T t) {
        getEm().remove(t);
        return t;
    }



}
