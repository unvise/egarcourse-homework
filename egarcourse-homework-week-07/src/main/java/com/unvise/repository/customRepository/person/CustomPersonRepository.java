package com.unvise.repository.customRepository.person;

import com.unvise.domain.entity.Person;
import com.unvise.repository.customRepository.CustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomPersonRepository extends CustomRepository<Person, Long> {

    List<Person> findByName(String pattern);

    List<Person> findByEmailAndPhoneOrderByNameAsc(String emailPattern, String phonePattern);

    List<Person> findByEmailAndPhoneOrderByNameDesc(String pattern, String phonePattern);

}
