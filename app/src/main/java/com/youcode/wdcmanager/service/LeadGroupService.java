package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.LeadGroup;
import com.youcode.wdcmanager.repository.LeadGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeadGroupService {
    private final LeadGroupRepository repository;

    public LeadGroup create(LeadGroup leadGroup) {
        return repository.save(leadGroup);
    }

    public LeadGroup update(LeadGroup leadGroup) {
        return repository.save(leadGroup);
    }

    public LeadGroup findById(Short id) {
        return repository.findById(id).orElse(null);
    }

    public List<LeadGroup> findAll() {
        return repository.findAll();
    }

    public void deleteById(Short id) {
        repository.deleteById(id);
    }

    public List<LeadGroup> createAll(Iterable<LeadGroup> person){
        return repository.saveAll(person);
    }

}
