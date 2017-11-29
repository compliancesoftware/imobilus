package br.com.imobilus.admin.model;

import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entidade relacionada às fotos do progresso de uma construção
 * @author douglas.f.filho
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="building_photos")
public class BuildingPhotos implements br.com.imobilus.admin.model.Entity {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "foto", length = 15000000)
	private byte[] foto;
	
	@OneToOne
	@JoinColumn(name = "building_id")
	private Building buildingId;
	
	@Column(name = "photo_date", nullable = false)
	private Calendar photoDate;
	
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Building getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Building buildingId) {
		this.buildingId = buildingId;
	}

	public Calendar getPhotoDate() {
		return photoDate;
	}

	public void setPhotoDate(Calendar photoDate) {
		this.photoDate = photoDate;
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
			return "BuildingPhotos [id=" + id + ", foto=" + Arrays.toString(foto) + ", buildingId=" + buildingId
					+ ", photoDate=" + photoDate + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt="
					+ updatedAt + ", updatedBy=" + updatedBy + "]";
		} catch(NullPointerException e) {
			return "BuildingPhotos [null]";
		}
	}
}