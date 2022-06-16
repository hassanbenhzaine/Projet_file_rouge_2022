package com.youcode.crm.repository;

import com.youcode.crm.entity.TransportType;
import com.youcode.crm.enums.MODE_OF_TRANSPORT_CODE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportTypeRepository extends JpaRepository<TransportType, MODE_OF_TRANSPORT_CODE> {
}
