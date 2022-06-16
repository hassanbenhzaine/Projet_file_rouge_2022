package com.youcode.wdcmanager.service;

import com.youcode.wdcmanager.entity.Role;
import com.youcode.wdcmanager.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role create(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        return roleRepository.save(role);
    }

    public Role findById(Short id) {
        return roleRepository.findById(id).orElse(null);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public void deleteById(Short id) {
        roleRepository.deleteById(id);
    }

    public List<Role> createAll(Iterable<Role> person){
        return roleRepository.saveAll(person);
    }

}
