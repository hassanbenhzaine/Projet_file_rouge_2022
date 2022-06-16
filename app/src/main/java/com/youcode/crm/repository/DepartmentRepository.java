package com.youcode.crm.repository;

import com.youcode.crm.entity.Department;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

//    @EntityGraph(attributePaths = {"manager"})
    List<Department> getDepartmentsByNameContainingIgnoreCase(String name, Pageable pageable);

//    @EntityGraph(attributePaths = {"manager"})
    List<Department> findAllBy(Pageable pageable);

//    @EntityGraph(attributePaths = {"manager"})
    Optional<Department> findById(Long id);

}
