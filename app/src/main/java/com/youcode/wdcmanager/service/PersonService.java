package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.Person;
import com.youcode.wdcmanager.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;

    public Person create(Person person) {
        return repository.save(person);
    }

    public Person update(Person person) {
        return repository.save(person);
    }

    public Person findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Person> createAll(Iterable<Person> person){
        return repository.saveAll(person);
    }

}
