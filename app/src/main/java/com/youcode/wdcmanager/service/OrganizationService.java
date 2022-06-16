package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.Organization;
import com.youcode.wdcmanager.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository repository;

    public Organization create(Organization organization) {
        return repository.save(organization);
    }

    public Organization update(Organization organization) {
        return repository.save(organization);
    }

    public Organization findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<Organization> findAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Organization> createAll(Iterable<Organization> person){
        return repository.saveAll(person);
    }

}
