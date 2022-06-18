package com.unvise.repository.customRepository.person;

import com.unvise.domain.entity.Person;
import com.unvise.repository.customRepository.AbstractCustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomPersonRepositoryImpl extends AbstractCustomRepository implements CustomPersonRepository {

    @Override
    public List<Person> findAll() {
        return getEntityManager()
                .createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    @Override
    public List<Person> findByName(String pattern) {
        return getEntityManager()
                .createQuery("select p from Person p " +
                        "where p.name like :pattern", Person.class)
                .setParameter("pattern", "%" + pattern + "%")
                .getResultList();
    }

    @Override
    public List<Person> findByEmailAndPhoneOrderByNameAsc(String emailPattern, String phonePattern) {
        return getEntityManager()
                .createQuery("select p from Person p " +
                        "where p.email like :emailPattern and p.phone like :phonePattern " +
                        "order by p.name asc", Person.class)
                .setParameter("emailPattern", "%" + emailPattern + "%")
                .setParameter("phonePattern", "%" + phonePattern + "%")
                .getResultList();
    }

    @Override
    public List<Person> findByEmailAndPhoneOrderByNameDesc(String emailPattern, String phonePattern) {
        return getEntityManager()
                .createQuery("select p from Person p " +
                        "where p.email like :emailPattern and p.phone like :phonePattern " +
                        "order by p.name desc", Person.class)
                .setParameter("emailPattern", "%" + emailPattern + "%")
                .setParameter("phonePattern", "%" + phonePattern + "%")
                .getResultList();
    }
}
