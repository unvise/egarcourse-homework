package com.unvise.service;

import com.unvise.domain.entity.Person;
import com.unvise.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService implements BaseService<Person, Long> {

    private final PersonRepository personRepository;


    @Override
    @Transactional
    public List<Person> getAll() {
        List<Person> persons = Collections.emptyList();
        try {
            log.debug("Getting all rows...");
            persons = personRepository.findAll();
            log.debug("All rows are found");
        } catch (Exception e) {
            log.error("Exception occurred while getting rows", new RuntimeException(e));
        }
        return persons;
    }

    @Override
    @Transactional
    public Optional<Person> getById(Long aLong) {
        Optional<Person> person = Optional.empty();
        try {
            log.debug("Getting row by id: {}", aLong);
            person = personRepository.findById(aLong);
            log.debug("Row is found");
        } catch (Exception e) {
            log.error("Exception occurred while getting row with id: {}", aLong, new RuntimeException(e));

        }
        return person;
    }

    @Override
    @Transactional
    public Person save(Person instance) {
        Person personToSave = null;
        try {
            log.info("Saving instance: {}", instance);
            personToSave = personRepository.save(instance);
            log.info("Successfully save new instance: {}", instance);
        } catch (Exception e) {
            log.error("Can't save a new instance {}", instance, new RuntimeException(e));
        }
        return personToSave;
    }

    @Override
    @Transactional
    public Person deleteById(Long aLong) {
        Optional<Person> person = getById(aLong);
        try {
            log.info("Trying to deleting row with id: {}", aLong);
            person.ifPresent(personRepository::delete);
            log.info("Row with id: {} is deleted", aLong);
        } catch (Exception e) {
            log.error("Can't delete a with id: {}", aLong, new RuntimeException(e));
        }
        return person.orElse(null);
    }

}
