package com.invoicemgmt.dto;

public class InvoiceDto {
	
	private Long id;
	private Double amount;
	private String applyDate;
	private String beneficiaryName;
	private Long beneficiaryAccountNumber;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public Long getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}
	public void setBeneficiaryAccountNumber(Long beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}
	@Override
	public String toString() {
		return "InvoiceDto [id=" + id + ", amount=" + amount + ", applyDate=" + applyDate + ", beneficiaryName="
				+ beneficiaryName + ", beneficiaryAccountNumber=" + beneficiaryAccountNumber + "]";
	}
	

}
