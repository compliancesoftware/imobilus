package br.com.imobilus.admin.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Configuração usada pela classe EmailUtil pra envio de e-mails
 * @author douglas.f.filho
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="mailing")
public class Mailing implements br.com.imobilus.admin.model.Entity {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "field_key", nullable = false, unique = true)
	private String key;
	@Column(name = "field_value", nullable = false)
	private String value;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
		return "Mailing [id=" + id + ", key=" + key + ", value=" + value + ", createdAt=" + createdAt + ", createdBy="
				+ createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + "]";
	}
	
}
