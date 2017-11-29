package br.com.imobilus.admin.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.imobilus.admin.util.enums.BuildModel;
import br.com.imobilus.admin.util.enums.BuildType;
import br.com.imobilus.admin.util.enums.FloorCategory;
import br.com.imobilus.admin.util.enums.SunLocation;

/**
 * Representação de um imóvel que cabe em um empreendimento.
 * @author douglas.f.filho
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="realty")
public class Realty implements br.com.imobilus.admin.model.Entity {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "number", nullable = false)
	private String number;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "available", nullable = false)
	private boolean available;
	
	@OneToOne
	@JoinColumn(name = "achievement_id", nullable = false)
	private Achievement achievementId;
	
	@Column(name = "floor", nullable = false)
	private String floor;
	
	@Column(name = "sun_location", nullable = false)
	private String sunLocation;
	
	@Column(name = "build_type", nullable = false)
	private String buildType;
	
	@Column(name = "build_model", nullable = false)
	private String buildModel;
	
	@Column(name = "value", nullable = false)
	private double value;
	
	@Column(name = "length", nullable = false)
	private double length;
	
	@Column(name = "parking_lots", nullable = false)
	private Integer parkingLots;
	
	@Column(name = "bedrooms_without_suite", nullable = false)
	private Integer bedroomsWithoutSuite;
	
	@Column(name = "bedrooms_with_suite", nullable = false)
	private Integer bedroomsWithSuite;
	
	@Column(name = "dining_rooms", nullable = false)
	private Integer diningRooms;
	
	@Column(name = "living_rooms", nullable = false)
	private Integer livingRooms;
	
	@Column(name = "has_home_office", nullable = false)
	private boolean hasHomeOffice;
	
	@Column(name = "social_bathrooms", nullable = false)
	private Integer socialBathrooms;
	
	@Column(name = "has_balcony", nullable = false)
	private boolean hasBalcony;
	
	@Column(name = "floor_category", nullable = false)
	private String floorCategory;
	
	@Column(name = "captured_at", nullable = false)
	private Calendar capturedAt;
	
	@OneToOne
	@JoinColumn(name = "captured_by", nullable = false)
	private User capturedBy;
	
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Achievement getAchievementId() {
		return achievementId;
	}

	public void setAchievementId(Achievement achievementId) {
		this.achievementId = achievementId;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getSunLocation() {
		return sunLocation;
	}

	public SunLocation getSunLocationAsEnum() {
		return SunLocation.valueOf(this.sunLocation);
	}
	
	public void setSunLocation(String sunLocation) {
		this.sunLocation = sunLocation;
	}

	public void setSunLocation(SunLocation sunLocation) {
		this.sunLocation = sunLocation.getValue();
	}
	
	public String getBuildType() {
		return buildType;
	}

	public BuildType getBuildTypeAsEnum() {
		return BuildType.valueOf(buildType);
	}
	
	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public void setBuildType(BuildType buildType) {
		this.buildType = buildType.getValue();
	}
	
	public String getBuildModel() {
		return buildModel;
	}

	public BuildModel getBuildModelAsEnum() {
		return BuildModel.valueOf(buildModel);
	}
	
	public void setBuildModel(String buildModel) {
		this.buildModel = buildModel;
	}

	public void setBuildModel(BuildModel buildModel) {
		this.buildModel = buildModel.getValue();
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public Integer getParkingLots() {
		return parkingLots;
	}

	public void setParkingLots(Integer parkingLots) {
		this.parkingLots = parkingLots;
	}

	public Integer getBedroomsWithoutSuite() {
		return bedroomsWithoutSuite;
	}

	public void setBedroomsWithoutSuite(Integer bedroomsWithoutSuite) {
		this.bedroomsWithoutSuite = bedroomsWithoutSuite;
	}

	public Integer getBedroomsWithSuite() {
		return bedroomsWithSuite;
	}

	public void setBedroomsWithSuite(Integer bedroomsWithSuite) {
		this.bedroomsWithSuite = bedroomsWithSuite;
	}

	public Integer getDiningRooms() {
		return diningRooms;
	}

	public void setDiningRooms(Integer diningRooms) {
		this.diningRooms = diningRooms;
	}

	public Integer getLivingRooms() {
		return livingRooms;
	}

	public void setLivingRooms(Integer livingRooms) {
		this.livingRooms = livingRooms;
	}

	public boolean isHasHomeOffice() {
		return hasHomeOffice;
	}

	public void setHasHomeOffice(boolean hasHomeOffice) {
		this.hasHomeOffice = hasHomeOffice;
	}

	public Integer getSocialBathrooms() {
		return socialBathrooms;
	}

	public void setSocialBathrooms(Integer socialBathrooms) {
		this.socialBathrooms = socialBathrooms;
	}

	public boolean isHasBalcony() {
		return hasBalcony;
	}

	public void setHasBalcony(boolean hasBalcony) {
		this.hasBalcony = hasBalcony;
	}

	public String getFloorCategory() {
		return floorCategory;
	}

	public FloorCategory getFloorCategoryAsEnum() {
		return FloorCategory.valueOf(floorCategory);
	}
	
	public void setFloorCategory(String floorCategory) {
		this.floorCategory = floorCategory;
	}

	public void setFloorCategory(FloorCategory floorCategory) {
		this.floorCategory = floorCategory.getValue();
	}
	
	public Calendar getCapturedAt() {
		return capturedAt;
	}

	public void setCapturedAt(Calendar capturedAt) {
		this.capturedAt = capturedAt;
	}

	public User getCapturedBy() {
		return capturedBy;
	}

	public void setCapturedBy(User capturedBy) {
		this.capturedBy = capturedBy;
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
			return "Realty [id=" + id + ", number=" + number + ", description=" + description + ", available=" + available
					+ ", floor=" + floor + ", sunLocation=" + sunLocation + ", buildType=" + buildType + ", buildModel="
					+ buildModel + ", value=" + value + ", length=" + length + ", parkingLots=" + parkingLots
					+ ", bedroomsWithoutSuite=" + bedroomsWithoutSuite + ", bedroomsWithSuite=" + bedroomsWithSuite
					+ ", diningRooms=" + diningRooms + ", livingRooms=" + livingRooms + ", hasHomeOffice=" + hasHomeOffice
					+ ", socialBathrooms=" + socialBathrooms + ", hasBalcony=" + hasBalcony + ", floorCategory="
					+ floorCategory + ", capturedAt=" + capturedAt + ", capturedBy=" + capturedBy + ", createdAt="
					+ createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy
					+ "]";
		} catch(NullPointerException e) {
			return "Realty [null]";
		}
	}
}
