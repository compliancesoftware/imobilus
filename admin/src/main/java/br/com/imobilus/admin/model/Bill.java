package br.com.imobilus.admin.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.imobilus.admin.util.enums.BillPaymentForm;

@SuppressWarnings("serial")
@Entity
@Table(name="bill")
public class Bill implements br.com.imobilus.admin.model.Entity {
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "client", nullable = false)
	private Client client;
	
	@OneToOne
	@JoinColumn(name = "realty", nullable = false)
	private Realty realty;
	
	@OneToOne
	@JoinColumn(name = "seller", nullable = false)
	private User seller;
	
	@OneToOne
	@JoinColumn(name = "financing_agency_id")
	private FinancingAgency financingAgencyId;
	
	@Column(name = "pay_day", nullable = false)
	private Calendar payDay;
	
	@Column(name = "bill_number", nullable = false)
	private String billNumber;
	
	@Column(name = "barcode", nullable = false)
	private String barcode;
	
	@Column(name = "in_installment", nullable = false)
	private Integer inInstallment;
	
	@Column(name = "total_installment", nullable = false)
	private Integer totalInstallment;
	
	@Column(name = "status", nullable = false)
	private Integer status;
	
	@Column(name = "payment_form", nullable = false)
	private String paymentForm;
	
	@Column(name = "value", nullable = false)
	private double value;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "created_at", nullable = false)
	private Calendar createdAt;
	
	@OneToOne
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;
	
	@Column(name = "updated_at", nullable = false)
	private Calendar updatedAt;
	
	@OneToOne
	@JoinColumn(name = "updated_by", nullable = false)
	private User updatedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Realty getRealty() {
		return realty;
	}

	public void setRealty(Realty realty) {
		this.realty = realty;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public FinancingAgency getFinancingAgencyId() {
		return financingAgencyId;
	}

	public void setFinancingAgencyId(FinancingAgency financingAgencyId) {
		this.financingAgencyId = financingAgencyId;
	}

	public Calendar getPayDay() {
		return payDay;
	}

	public void setPayDay(Calendar payDay) {
		this.payDay = payDay;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getInInstallment() {
		return inInstallment;
	}

	public void setInInstallment(Integer inInstallment) {
		this.inInstallment = inInstallment;
	}

	public Integer getTotalInstallment() {
		return totalInstallment;
	}

	public void setTotalInstallment(Integer totalInstallment) {
		this.totalInstallment = totalInstallment;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPaymentForm() {
		return paymentForm;
	}

	public BillPaymentForm getPaymentFormAsEnum() {
		return BillPaymentForm.valueOf(this.paymentForm);
	}
	
	public void setPaymentForm(String paymentForm) {
		this.paymentForm = paymentForm;
	}

	public void setPaymentForm(BillPaymentForm paymentForm) {
		this.paymentForm = paymentForm.getValue();
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Calendar getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		try {
			return "Bill [id=" + id + ", client=" + client + ", realty=" + realty + ", seller=" + seller
					+ ", financingAgencyId=" + financingAgencyId + ", payDay=" + payDay + ", billNumber=" + billNumber
					+ ", barcode=" + barcode + ", inInstallment=" + inInstallment + ", totalInstallment=" + totalInstallment
					+ ", status=" + status + ", paymentForm=" + paymentForm + ", value=" + value + ", description="
					+ description + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt
					+ ", updatedBy=" + updatedBy + "]";
		} catch(NullPointerException e) {
			return "Bill [null]";
		}
	}
}
