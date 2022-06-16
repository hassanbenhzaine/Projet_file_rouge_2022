package com.youcode.wdcmanager.repository;

import com.youcode.wdcmanager.entity.LeadGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadGroupRepository extends JpaRepository<LeadGroup, Short> {

}
