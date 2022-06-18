package com.unvise.repository.customRepository;

import java.io.Serializable;
import java.util.List;

public interface CustomRepository<T, ID extends Serializable> {

    List<T> findAll();

}
