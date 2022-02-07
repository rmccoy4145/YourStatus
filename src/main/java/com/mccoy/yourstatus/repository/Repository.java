package com.mccoy.yourstatus.repository;

import com.mccoy.yourstatus.entity.User;
import com.mccoy.yourstatus.entity.UserFollow;

import javax.persistence.EntityManager;
import java.util.List;

public interface Repository<T> {
    T get(Long id);
    List<T> getAll();
    T add(T t);
    T update(T t);
    T remove(T t);
    List<T> getAllByUser(User user);

}
