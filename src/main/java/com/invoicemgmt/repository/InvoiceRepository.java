package com.invoicemgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoicemgmt.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
