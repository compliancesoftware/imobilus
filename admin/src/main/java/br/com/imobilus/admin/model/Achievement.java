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
@Table(name="achievement")
public class Achievement implements br.com.imobilus.admin.model.Entity {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company companyId;
	
	@OneToOne
	@JoinColumn(name = "address_id", nullable = false)
	private Address addressId;
	
	@Column(name = "start_at", nullable = false)
	private Calendar startAt;
	
	@Column(name = "end_at", nullable = false)
	private Calendar endAt;
	
	@Column(name = "presentation_image", length = 15000000)
	private String presentationImage;
	
	@Column(name = "social_elevators", nullable = false)
	private Integer socialElevators;
	
	@Column(name = "service_elevators", nullable = false)
	private Integer serviceElevators;
	
	@Column(name = "has_accessibility", nullable = false)
	private boolean hasAccessibility;
	
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

	public Company getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Company companyId) {
		this.companyId = companyId;
	}

	public Address getAddressId() {
		return addressId;
	}

	public void setAddressId(Address addressId) {
		this.addressId = addressId;
	}

	public Calendar getStartAt() {
		return startAt;
	}

	public void setStartAt(Calendar startAt) {
		this.startAt = startAt;
	}

	public Calendar getEndAt() {
		return endAt;
	}

	public void setEndAt(Calendar endAt) {
		this.endAt = endAt;
	}

	public String getPresentationImage() {
		return presentationImage;
	}

	public void setPresentationImage(String presentationImage) {
		this.presentationImage = presentationImage;
	}

	public Integer getSocialElevators() {
		return socialElevators;
	}

	public void setSocialElevators(Integer socialElevators) {
		this.socialElevators = socialElevators;
	}

	public Integer getServiceElevators() {
		return serviceElevators;
	}

	public void setServiceElevators(Integer serviceElevators) {
		this.serviceElevators = serviceElevators;
	}

	public boolean isHasAccessibility() {
		return hasAccessibility;
	}

	public void setHasAccessibility(boolean hasAccessibility) {
		this.hasAccessibility = hasAccessibility;
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
			return "Achievement [id=" + id + ", name=" + name + ", addressId=" + addressId + ", startAt=" + startAt
					+ ", endAt=" + endAt + ", presentationImage=" + presentationImage
					+ ", socialElevators=" + socialElevators + ", serviceElevators=" + serviceElevators
					+ ", hasAccessibility=" + hasAccessibility + ", createdAt=" + createdAt + ", createdBy=" + createdBy
					+ ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + "]";
		} catch(NullPointerException e) {
			return "Achievement [id=" + id + ", name=" + name + "]";
		}
	}
}
