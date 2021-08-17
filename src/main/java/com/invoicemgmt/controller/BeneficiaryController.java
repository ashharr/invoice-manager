package com.invoicemgmt.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.invoicemgmt.model.Beneficiary;
import com.invoicemgmt.repository.BeneficiaryRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BeneficiaryController {
	
	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	
	@PostMapping("/beneficiary")
	public Beneficiary postBeneficiary(@RequestBody Beneficiary beneficiary) {
		return beneficiaryRepository.save(beneficiary);
	}
	
	@GetMapping("/beneficiary")
	public List<Beneficiary> getAllBeneficiary(){
		return beneficiaryRepository.findAll();
	}
	
	@GetMapping("/beneficiary/{id}")
	public Beneficiary getById(@PathVariable("id") Long id){
		return beneficiaryRepository.getById(id);
	}
	
	@PutMapping("/beneficiary/{id}")
	public Beneficiary updateBeneficiary(@RequestBody Beneficiary newBeneficiary, @PathVariable("id") Long id){
		Beneficiary beneficiaryDB = beneficiaryRepository.getById(id);
		beneficiaryDB.setBeneficiaryAccountNumber(newBeneficiary.getBeneficiaryAccountNumber());
		beneficiaryDB.setName(newBeneficiary.getName());
		return beneficiaryRepository.save(beneficiaryDB);
	}
	
	@DeleteMapping("/beneficiary/{id}")
	public void  deleteById(@PathVariable("id") Long id){
		beneficiaryRepository.deleteById(id);
	}
}
