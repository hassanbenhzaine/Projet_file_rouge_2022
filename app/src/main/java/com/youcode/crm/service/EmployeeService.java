package com.youcode.crm.service;

import com.youcode.crm.entity.Employee;
import com.youcode.crm.entity.dto.EmployeeDTO;
import com.youcode.crm.mapper.EmployeeMapper;
import com.youcode.crm.repository.EmployeeRepository;
import com.youcode.crm.security.registration.token.ConfirmationToken;
import com.youcode.crm.security.registration.token.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Mateusz Milewczyk (agiklo)
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService, CurrentTimeInterface {

    /**
     * were injected by the constructor using the lombok @AllArgsContrustor annotation
     */
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmployeeMapper employeeMapper;

    private static final String USER_NOT_FOUND_MSG =
            "user with email %s not found";


    /**
     * @param email email of the user
     * @return user found via email
     * @throws UsernameNotFoundException if user does not exist in database
     */
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return employeeRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    /**
     * The method checks if the user with the given e-mail already exists in the database,
     * if it exists, it throws an exception. However, if the user does not exist in the database,
     * the method adds the user and creates a token that is needfor confirmation.
     * @param employee requestbody of the employee to be saved
     * @throws IllegalStateException if email already exists in the database
     *
     * @return token needed for enable account
     */
    public UUID signUpUser(Employee employee){
        boolean userExists = employeeRepository.findByEmail(employee.getEmail()).isPresent();
        if (userExists){
            //TODO: IF USER NOT CONFIRMED, SEND EMAIL AGAIN
            throw new IllegalStateException(
                    String.format("Email %s already taken", employee.getEmail()));
        }

        String encodedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        employeeRepository.save(employee);

        UUID token = UUID.randomUUID();
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                                .token(token)
                                .createdAt(LocalDateTime.now())
                                .expiresAt(LocalDateTime.now().plusMinutes(15))
                                .employee(employee)
                                .build();

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public List<Employee> addNewEmployes(Iterable<Employee> employees){
       return employeeRepository.saveAll(employees);
    }

    /**
     * The task of the method is enable user in the database after confirming the account
     * @param email email of the user
     * @return enable user account
     */
    public int enableUser(String email) {
        return employeeRepository.enableUser(email);
    }

    /**
     * The method is to retrieve all employee from the database and display them.
     *
     * After downloading all the data about the employee,
     * the data is mapped to dto which will display only those needed
     * @return list of all employees with specification of data in EmployeeDTO
     */
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees(Pageable pageable){
        return employeeRepository.findAllBy(pageable)
                .stream()
                .map(employeeMapper::mapEmployeeToDto)
                .collect(Collectors.toList());
    }

    /**
     * The method is to download a specific employee from the database and display it.
     * After downloading all the data about the employee,
     * the data is mapped to dto which will display only those needed
     *
     * @param id id of the employee to be searched for
     * @throws ResponseStatusException if the id of the employee you are looking for does not exist throws 404 status
     * @return detailed data about a specific employee
     */
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee cannot be found, the specified id does not exist"));
        return employeeMapper.mapEmployeeToDto(employee);
    }

    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee cannot be found, the specified email does not exist"));
        return employeeMapper.mapEmployeeToDto(employee);
    }

    /**
     * The method is to retrieve employee whose have the firstname specified by the user.
     * After downloading all the data about the employee,
     * the data is mapped to dto which will display only those needed
     * @param firstName firstname of the employee
     * @return details of specific employee
     */
    @Transactional(readOnly = true)
    public List<EmployeeDTO> findEmployeesByFirstname(String firstName, Pageable pageable) {
        return employeeRepository.findUserByFirstNameContainingIgnoreCase(firstName, pageable)
                .stream()
                .map(employeeMapper::mapEmployeeToDto)
                .collect(Collectors.toList());
    }

    /**
     * Method deletes the selected employee by id
     * @param id id of the employee to be deleted
     */
    public void deleteEmployeeById(Long id) {
        try{
            employeeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("The specified id does not exist");
        }
    }

    @Transactional
    public EmployeeDTO editEmployee(EmployeeDTO employee){
        Employee mappedEmployee = employeeMapper.mapEmployeeDtoToEmployee(employee);
        Employee editedEmployee = employeeRepository.findById(mappedEmployee.getId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee cannot be found"));

        editedEmployee.setFirstName(mappedEmployee.getFirstName());
        editedEmployee.setLastName(mappedEmployee.getLastName());
        editedEmployee.setEmail(mappedEmployee.getEmail());
        editedEmployee.setPassword(mappedEmployee.getPassword());
        editedEmployee.setUserRole(mappedEmployee.getUserRole());
        editedEmployee.setCin(mappedEmployee.getCin());
        editedEmployee.setGender(mappedEmployee.getGender());
        editedEmployee.setBirthdate(mappedEmployee.getBirthdate());
        editedEmployee.setSalary(mappedEmployee.getSalary());
        editedEmployee.setPhone(mappedEmployee.getPhone());
        editedEmployee.setIsLocked(mappedEmployee.getIsLocked());
        editedEmployee.setIsEnabled(mappedEmployee.getIsEnabled());
        editedEmployee.setDepartment(mappedEmployee.getDepartment());

        return employeeMapper.mapEmployeeToDto(editedEmployee);
    }

    public EmployeeDTO addNewEmploye(EmployeeDTO employee) {
        Employee savedEmployee = employeeRepository.save(employeeMapper.mapEmployeeDtoToEmployee(employee));
        return employeeMapper.mapEmployeeToDto(savedEmployee);
    }
}
