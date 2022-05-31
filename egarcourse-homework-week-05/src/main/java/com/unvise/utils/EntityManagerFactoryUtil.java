package com.unvise.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

public class EntityManagerFactoryUtil {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory entityManagerFactory() {
        if (Objects.isNull(entityManagerFactory)) {
            entityManagerFactory = Persistence.createEntityManagerFactory("airlines");
        }
        return entityManagerFactory;
    }

}
