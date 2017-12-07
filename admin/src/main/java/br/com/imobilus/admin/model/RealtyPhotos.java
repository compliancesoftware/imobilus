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
@Table(name="realty_photos")
public class RealtyPhotos implements br.com.imobilus.admin.model.Entity {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "foto", length = 15000000)
	private String foto;
	
	@OneToOne
	@JoinColumn(name = "realty_id")
	private Realty realtyId;
	
	@Column(name = "photo_date", nullable = false)
	private Calendar photoDate;
	
	@Column(name = "created_at", nullable = false)
	private Calendar createdAt;
	
	@OneToOne
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Realty getRealtyId() {
		return realtyId;
	}

	public void setRealtyId(Realty realtyId) {
		this.realtyId = realtyId;
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

	@Override
	public String toString() {
		try {
			return "RealtyPhotos [id=" + id + ", foto=" + foto + ", realtyId=" + realtyId + ", photoDate="
					+ photoDate + ", createdAt=" + createdAt + ", createdBy=" + createdBy + "]";
		} catch(NullPointerException e) {
			return "RealtyPhotos [null]";
		}
	}
}
