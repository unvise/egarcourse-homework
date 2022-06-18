package com.unvise.repository.customRepository;

import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractCustomRepository {

    @PersistenceContext
    @Getter
    private EntityManager entityManager;

}
