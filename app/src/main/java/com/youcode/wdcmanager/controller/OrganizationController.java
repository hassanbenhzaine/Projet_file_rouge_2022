package com.youcode.wdcmanager.controller;

import com.youcode.wdcmanager.entity.Organization;
import com.youcode.wdcmanager.exception.EntityNotFoundException;
import com.youcode.wdcmanager.service.OrganizationService;
import com.youcode.wdcmanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Organization>> getAll() {
        return new ResponseEntity<>(organizationService.findAll(), HttpStatus.OK);
    }

    @PostMapping(consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Organization> create(@RequestBody Organization role) {
        Organization createdRole = Optional.ofNullable(organizationService.create(role))
                .orElseThrow(
                        () -> {
                            throw new EntityNotFoundException("Organization not found");
                        }
                );

        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Organization> update(@PathVariable Long id, @RequestBody Organization role) {
        Organization updatedRole = Optional.ofNullable(organizationService.update(role))
                .orElseThrow(
                        () -> {
                            throw new EntityNotFoundException("Organization not found with id " + id);
                        }
                );
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        organizationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Organization> getOne(@PathVariable Integer id) {
        Organization foundRole = Optional.ofNullable(organizationService.findById(id))
                .orElseThrow(
                        () -> {
                            throw new EntityNotFoundException("Organization not found with id " + id);
                        }
                );
        return new ResponseEntity<>(foundRole, HttpStatus.CREATED);
    }

}
