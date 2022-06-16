package com.youcode.crm.controller;

import com.youcode.crm.entity.Department;
import com.youcode.crm.entity.Employee;
import com.youcode.crm.entity.dto.DepartmentDTO;
import com.youcode.crm.entity.dto.EmployeeDTO;
import com.youcode.crm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.youcode.crm.controller.ApiMapping.EMPLOYEES_REST_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(EMPLOYEES_REST_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(Pageable pageable){
        return status(HttpStatus.OK).body(employeeService.getAllEmployees(pageable));
    }

    @GetMapping(path = "/{id}", produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable final Long id){
        return status(HttpStatus.OK).body(employeeService.getEmployeeById(id));
    }

    @GetMapping(path= "/firstname",produces=APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<EmployeeDTO>> findEmployeesByFirstname(@RequestParam final String firstName, Pageable pageable) {
        return status(HttpStatus.OK).body(employeeService.findEmployeesByFirstname(firstName, pageable));
    }

    @PutMapping(produces=APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<EmployeeDTO> editEmployeeContent(@RequestBody final EmployeeDTO department){
        return status(HttpStatus.OK).body(employeeService.editEmployee(department));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteEmployeeById(@PathVariable final Long id) {
        employeeService.deleteEmployeeById(id);
    }

    @PostMapping(consumes=APPLICATION_JSON_VALUE, produces=APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<EmployeeDTO> postNewEmployee(@RequestBody final EmployeeDTO employee) {
        return status(HttpStatus.OK).body(employeeService.addNewEmploye(employee));
    }

}
