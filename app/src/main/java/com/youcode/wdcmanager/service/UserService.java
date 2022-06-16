package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.User;
import com.youcode.wdcmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User create(User person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return repository.save(person);
    }

    public List<User> createAll(Iterable<User> person){
        return repository.saveAll(person);
    }

    public User update(User person) {
        return repository.save(person);
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User findById(String id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
