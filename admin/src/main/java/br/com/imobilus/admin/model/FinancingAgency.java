package br.com.imobilus.admin.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="financing_agency")
public class FinancingAgency implements br.com.imobilus.admin.model.Entity  {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "code", nullable = false)
	private Integer code;
	
	@OneToOne
	@JoinColumn(name = "address_id", nullable = false)
	private Address addressId;
	
	@Column(name = "logo", length = 15000000)
	private String logo;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Address getAddressId() {
		return addressId;
	}

	public void setAddressId(Address addressId) {
		this.addressId = addressId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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
			return "FinancingAgency [id=" + id + ", name=" + name + ", code=" + code + ", addressId=" + addressId
					+ ", logo=" + logo + ", createdAt=" + createdAt + ", createdBy=" + createdBy
					+ ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + "]";
		} catch(NullPointerException e) {
			return "FinancingAgency [null]";
		}
	}
}
