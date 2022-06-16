package com.youcode.crm.repository;

import com.youcode.crm.entity.SellingInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellingInvoiceRepository extends JpaRepository<SellingInvoice, Long> {
}
