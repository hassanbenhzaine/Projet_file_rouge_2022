package com.youcode.wdcmanager.controller;

import com.youcode.wdcmanager.entity.Role;
import com.youcode.wdcmanager.exception.EntityNotFoundException;
import com.youcode.wdcmanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> getAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @PostMapping(consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> create(@RequestBody Role role) {
        Role createdRole = Optional.ofNullable(roleService.create(role))
                .orElseThrow(
                        () -> {
                            throw new EntityNotFoundException("Role not found");
                        }
                );

        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> update(@PathVariable Long id, @RequestBody Role role) {
        Role updatedRole = Optional.ofNullable(roleService.update(role))
                .orElseThrow(
                        () -> {
                            throw new EntityNotFoundException("Role not found with id " + id);
                        }
                );
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Short id) {
        roleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getOne(@PathVariable Short id) {
        Role foundRole = Optional.ofNullable(roleService.findById(id))
                .orElseThrow(
                        () -> {
                            throw new EntityNotFoundException("Role not found with id " + id);
                        }
                );
        return new ResponseEntity<>(foundRole, HttpStatus.CREATED);
    }

}
