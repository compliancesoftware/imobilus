package br.com.imobilus.admin.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Modelo/entidade de endereços.
 * @author douglas.f.filho
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="address")
public class Address implements br.com.imobilus.admin.model.Entity {
	@Id
	@Column(name="cep")
	private Long cep;
	@Column(name="address", nullable = false)
	private String address;
	@Column(name="complement")
	private String complement;
	@Column(name="number")
	private Integer number;
	@Column(name="district")
	private String district;
	@Column(name="city", nullable = false)
	private String city;
	@Column(name="state", nullable = false)
	private String state;
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
	
	public Long getCep() {
		return cep;
	}
	
	public void setCep(Long cep) {
		this.cep = cep;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getComplement() {
		return complement;
	}
	
	public void setComplement(String complement) {
		this.complement = complement;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String getDistrict() {
		return district;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
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
		return "Address [cep=" + cep + ", address=" + address + ", complement=" + complement + ", number=" + number
				+ ", district=" + district + ", city=" + city + ", state=" + state + ", createdAt=" + createdAt
				+ ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + "]";
	}
}
