package com.youcode.crm.controller;

import com.youcode.crm.entity.Department;
import com.youcode.crm.entity.Product;
import com.youcode.crm.entity.dto.DepartmentDTO;
import com.youcode.crm.entity.dto.ProductDTO;
import com.youcode.crm.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youcode.crm.controller.ApiMapping.DEPARTMENTS_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(DEPARTMENTS_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping(produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(Pageable pageable){
        return status(HttpStatus.OK).body(departmentService.getAllDepartments(pageable));
    }

    @GetMapping(path = "/{id}", produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable final Long id){
        return status(HttpStatus.OK).body(departmentService.getDepartmentById(id));
    }

    @GetMapping(path = "/name", produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartmentsByName(@RequestParam final String name, Pageable pageable){
        return status(HttpStatus.OK).body(departmentService.getAllDepartmentsByName(name, pageable));
    }

    @PostMapping(consumes=APPLICATION_JSON_VALUE, produces=APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<DepartmentDTO> postNewDepartment(@RequestBody final DepartmentDTO department) {
        return status(HttpStatus.OK).body(departmentService.addNewDepartment(department));
    }

    @PutMapping(produces=APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<DepartmentDTO> editDepartmentContent(@RequestBody final DepartmentDTO department){
        return status(HttpStatus.OK).body(departmentService.editDepartment(department));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteDepartmentById(@PathVariable final Long id) {
        departmentService.deleteDepartmentById(id);
    }

}
