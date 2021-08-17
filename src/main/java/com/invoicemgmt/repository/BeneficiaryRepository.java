package com.invoicemgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoicemgmt.model.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

}
