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
@Table(name="orderly")
public class Orderly implements br.com.imobilus.admin.model.Entity {
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "broker", nullable = false)
	private User broker;
	
	@OneToOne
	@JoinColumn(name = "realty", nullable = false)
	private Realty realty;
	
	@Column(name = "start_at", nullable = false)
	private Calendar startAt;
	
	@Column(name = "end_at", nullable = false)
	private Calendar endAt;
	
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

	public User getBroker() {
		return broker;
	}

	public void setBroker(User broker) {
		this.broker = broker;
	}

	public Realty getRealty() {
		return realty;
	}

	public void setRealty(Realty realty) {
		this.realty = realty;
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
			return "Orderly [id=" + id + ", broker=" + broker + ", realty=" + realty + ", startAt=" + startAt + ", endAt="
					+ endAt + ", createdAt=" + createdAt + ", createdBy=" + createdBy + ", updatedAt=" + updatedAt
					+ ", updatedBy=" + updatedBy + "]";
		} catch(NullPointerException e) {
			return "Orderly [null]";
		}
	}
	
}
