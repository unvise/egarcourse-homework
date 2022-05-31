package com.unvise.dao;

import com.unvise.utils.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public class BaseDaoImpl<T, ID> implements BaseDao<T, ID> {

    protected final EntityManagerFactory entityManagerFactory =
            EntityManagerFactoryUtil.entityManagerFactory();

    private Class<T> clazz;

    @Override
    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        return manager.createQuery("select i from " + clazz.getSimpleName() + " i").getResultList();
    }

    @Override
    public Optional<T> findById(ID id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        return Optional.ofNullable(manager.find(clazz, id));
    }

    @Override
    public T save(T instance) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();

        T newInstance;

        try {
            Field id = clazz.getDeclaredField("id");
            id.setAccessible(true);
            if (id.get(instance) == null) {
                manager.persist(instance);
                newInstance = instance;
            } else {
                newInstance = manager.merge(instance);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        manager.getTransaction().commit();
        return newInstance;
    }

    @Override
    public Optional<T> deleteById(ID id) throws RuntimeException {
        EntityManager manager = entityManagerFactory.createEntityManager();
        Optional<T> result = Optional.ofNullable(manager.find(clazz, id));

        manager.getTransaction().begin();
        result.ifPresentOrElse(
                manager::remove,
                () -> {
                    throw new RuntimeException("Cannot find existing rows with putted id");
                }
        );
        manager.getTransaction().commit();

        return result;
    }
}
