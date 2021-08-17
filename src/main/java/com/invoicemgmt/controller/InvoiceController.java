package com.invoicemgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invoicemgmt.model.Beneficiary;
import com.invoicemgmt.model.Invoice;
import com.invoicemgmt.repository.BeneficiaryRepository;
import com.invoicemgmt.repository.InvoiceRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class InvoiceController {
	
	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@PostMapping("/invoice/{id}")
	public Invoice postInvoice(@RequestBody Invoice invoice, @PathVariable("id") Long beneficiaryId) {
		Beneficiary beneficiary = beneficiaryRepository.getById(beneficiaryId);
		invoice.setBeneficiary(beneficiary); 
		return invoiceRepository.save(invoice);
	}
	
	@GetMapping("/invoice")
	public List<Invoice> getAllInvoice(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10000") Integer size){
		Pageable pageable = PageRequest.of(page, size);
		List<Invoice> list = invoiceRepository.findAll(pageable).getContent();
		return list;
	}
	
	@GetMapping("/invoice/{id}")
	public Invoice getAllInvoice(@PathVariable("id") Long id){
		return invoiceRepository.getById(id);
	}
	
	
//	@GetMapping("/product/vendor/{vid}")
//	public List<Product> getAllProductsByVendor(@PathVariable("vid") Long vid){
//		return productRepository.findByVendorId(vid);
//	}
	
//	@GetMapping("/product/{pid}")
//	public Product getSingleProduct(@PathVariable("pid") Long pid) {
//		return productRepository.getById(pid);
//	}
	
	@PutMapping("invoice/{id}")
	public Invoice updateInvoice(@PathVariable("id") Long id, @PathVariable("id") Long bid, @RequestBody Invoice newInvoice) {
		Invoice invoice= invoiceRepository.getById(id);
		invoice.setAmount(newInvoice.getAmount());
		invoice.setApplyDate(newInvoice.getApplyDate());
		return invoiceRepository.save(invoice);
	}
	
	@DeleteMapping("/invoice/{id}")
	public void deleteInvoice(@PathVariable("id") Long id) {
		invoiceRepository.deleteById(id);
	}
	
//	@GetMapping("product/customer/{cid}")
//	public List<Product> getProductByCustomerId(@PathVariable("cid")Long cid) {
//		return productRepository.getProductByCustomerId(cid);
//	}
	
}
