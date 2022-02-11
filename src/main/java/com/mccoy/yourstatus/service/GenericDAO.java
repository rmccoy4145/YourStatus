package com.mccoy.yourstatus.service;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    public T get(Long id);
    public List<T> getAll();
    public T add(T t);
    public T update(T t);
    public T remove(T t);
}
