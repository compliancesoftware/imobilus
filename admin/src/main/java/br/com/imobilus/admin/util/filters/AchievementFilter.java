package br.com.imobilus.admin.util.filters;

import java.util.Calendar;

public class AchievementFilter {
	
	private String name;
	
	private String company;
	
	private Long cep;
	
	private String address;

	private String district;
	
	private String city;
	
	private String state;
	
	private Calendar startAtInitialInterval;
	
	private Calendar startAtFinalInterval;
	
	private Calendar endAtInitialInterval;
	
	private Calendar endAtFinalInterval;
	
	private Integer socialElevators;
	
	private Integer serviceElevators;
	
	private Boolean hasAccessibility;

	public String getName() {
		if(this.name != null && !this.name.equals("")) {
			return this.name;
		}
		else {
			return "";
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		if(this.company != null && !this.company.equals("")) {
			return this.company;
		}
		else {
			return "";
		}
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Long getCep() {
		if(this.cep != null && !this.cep.equals(null) && this.cep != 0) {
			return this.cep;
		}
		else {
			return null;
		}
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public String getAddress() {
		if(this.address != null && !this.address.equals("")) {
			return this.address;
		}
		else {
			return "";
		}
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		if(this.district != null && !this.district.equals("")) {
			return this.district;
		}
		else {
			return "";
		}
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		if(this.city != null && !this.city.equals("")) {
			return this.city;
		}
		else {
			return "";
		}
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		if(this.state != null && !this.state.equals("")) {
			return this.state;
		}
		else {
			return "";
		}
	}

	public void setState(String state) {
		this.state = state;
	}

	public Calendar getStartAtInitialInterval() {
		return startAtInitialInterval;
	}

	public void setStartAtInitialInterval(Calendar startAtInitialInterval) {
		this.startAtInitialInterval = startAtInitialInterval;
	}

	public Calendar getStartAtFinalInterval() {
		return startAtFinalInterval;
	}

	public void setStartAtFinalInterval(Calendar startAtFinalInterval) {
		this.startAtFinalInterval = startAtFinalInterval;
	}

	public Calendar getEndAtInitialInterval() {
		return endAtInitialInterval;
	}

	public void setEndAtInitialInterval(Calendar endAtInitialInterval) {
		this.endAtInitialInterval = endAtInitialInterval;
	}

	public Calendar getEndAtFinalInterval() {
		return endAtFinalInterval;
	}

	public void setEndAtFinalInterval(Calendar endAtFinalInterval) {
		this.endAtFinalInterval = endAtFinalInterval;
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

	public Boolean hasAccessibility() {
		return hasAccessibility;
	}

	public void setHasAccessibility(boolean hasAccessibility) {
		this.hasAccessibility = hasAccessibility;
	}

	@Override
	public String toString() {
		try {
			return "AchievementFilter [name=" + name + ", company=" + company + ", cep=" + cep + ", address=" + address
					+ ", district=" + district + ", city=" + city + ", state=" + state + ", startAtInitialInterval="
					+ startAtInitialInterval + ", startAtFinalInterval=" + startAtFinalInterval + ", endAtInitialInterval="
					+ endAtInitialInterval + ", endAtFinalInterval=" + endAtFinalInterval + ", socialElevators="
					+ socialElevators + ", serviceElevators=" + serviceElevators + ", hasAccessibility=" + hasAccessibility
					+ "]";
		} catch(NullPointerException e) {
			return "AchievementFilter [name=" + name + "]";
		}
	}
	
}
