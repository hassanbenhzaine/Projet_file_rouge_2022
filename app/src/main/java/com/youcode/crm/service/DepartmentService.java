package com.youcode.crm.service;

import com.youcode.crm.entity.Department;
import com.youcode.crm.entity.Product;
import com.youcode.crm.entity.dto.DepartmentDTO;
import com.youcode.crm.entity.dto.ProductDTO;
import com.youcode.crm.mapper.DepartmentMapper;
import com.youcode.crm.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService implements CurrentTimeInterface{

    /**
     * were injected by the constructor using the lombok @AllArgsContrustor annotation
     */
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    /**
     * The method is to retrieve all departments from the database and display them.
     *
     * After downloading all the data about the department,
     * the data is mapped to dto which will display only those needed
     * @return list of all departments with specification of data in DepartmentDTO
     */
    @Transactional(readOnly = true)
    public List<DepartmentDTO> getAllDepartments(Pageable pageable){
        return departmentRepository.findAllBy(pageable)
                .stream()
                .map(departmentMapper::mapDepartmentToDto)
                .collect(Collectors.toList());
    }

    /**
     * The method is to download a specific department from the database and display it.
     * After downloading all the data about the department,
     * the data is mapped to dto which will display only those needed
     *
     * @param id id of the department to be searched for
     * @throws ResponseStatusException if the id of the department you are looking for does not exist throws 404 status
     * @return detailed data about a specific department
     */
    @Transactional(readOnly = true)
    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department cannot be found, the specified id does not exist"));
        return departmentMapper.mapDepartmentToDto(department);
    }

    /**
     * The task of the method is to add a department to the database.
     * @param department requestbody of the department to be saved
     * @return saving the department to the database
     */
    public DepartmentDTO addNewDepartment(DepartmentDTO department) {
        Department savedDepartment = departmentRepository.save(departmentMapper.mapDepartmentDtoToDepartment(department));
        return departmentMapper.mapDepartmentToDto(savedDepartment);
    }

    /**
     * Method deletes the selected department by id
     * @param id id of the department to be deleted
     * @throws ResponseStatusException if id of the department is incorrect throws 404 status with message
     */
    public void deleteDepartmentById(Long id) {
        try{
            departmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The specified id does not exist");
        }
    }




    /**
     * The method is to retrieve departments whose have the name specified by the user.
     * After downloading all the data about the department,
     * the data is mapped to dto which will display only those needed
     * @param name name of the department
     * @return details of specific departments
     */
    public List<DepartmentDTO> getAllDepartmentsByName(String name, Pageable pageable) {
        return departmentRepository.getDepartmentsByNameContainingIgnoreCase(name, pageable)
                .stream()
                .map(departmentMapper::mapDepartmentToDto)
                .collect(Collectors.toList());
    }

    public List<Department> addNewDepartments(Iterable<Department> departments) {
        return departmentRepository.saveAll(departments);
    }

    @Transactional
    public DepartmentDTO editDepartment(DepartmentDTO department){
        Department mappedDepartment = departmentMapper.mapDepartmentDtoToDepartment(department);
        Department editedDepartment = departmentRepository.findById(mappedDepartment.getId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department cannot be found"));

        editedDepartment.setName(mappedDepartment.getName());
        editedDepartment.setCity(mappedDepartment.getCity());
        return departmentMapper.mapDepartmentToDto(editedDepartment);
    }
}
