package com.youcode.wdcmanager.repository;

import com.youcode.wdcmanager.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Deal, Long> {

}
