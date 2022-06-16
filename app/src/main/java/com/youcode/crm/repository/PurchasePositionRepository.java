package com.youcode.crm.repository;


import com.youcode.crm.entity.PurchasePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasePositionRepository extends JpaRepository<PurchasePosition, Long> {

    @Query(value = "SELECT pp FROM PurchasePosition pp WHERE pp.id = :Id")
    List<PurchasePosition> findAllById(@Param("Id") Long Id);
}
