package com.unvise.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID extends Serializable> {

    List<T> getAll();

    Optional<T> getById(ID id);

    T save(T instance);

    T deleteById(ID id);

}

