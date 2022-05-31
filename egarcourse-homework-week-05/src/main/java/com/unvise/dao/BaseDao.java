package com.unvise.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T, ID> {

    void setClazz(Class<T> clazz);

    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T instance);

    Optional<T> deleteById(ID id);

}
