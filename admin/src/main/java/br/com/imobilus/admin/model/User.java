package br.com.imobilus.admin.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Usuário do sistema.
 * @author douglas.f.filho
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = {"password"})
@SuppressWarnings("serial")
@Entity
@Table(name="user")
public class User  implements br.com.imobilus.admin.model.Entity {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "phone1", nullable = false, unique = true)
	private String phone1;
	@Column(name = "phone2", nullable = false, unique = true)
	private String phone2;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "foto", length = 15000000)
	private String foto;
	@OneToOne
	@JoinColumn(name = "permission")
	private Permission permission;
	@Column(name = "created_at", nullable = false)
	private Calendar createdAt;
	@Column(name = "updated_at", nullable = false)
	private Calendar updatedAt;
	@Column(name = "last_access", nullable = false)
	private Calendar lastAccess;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public Calendar getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}
	public Calendar getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Calendar getLastAccess() {
		return lastAccess;
	}
	public void setLastAccess(Calendar lastAccess) {
		this.lastAccess = lastAccess;
	}
	@Override
	public String toString() {
		try {
			return "User [id=" + id + ", name=" + name + ", password=" + password + ", phone1=" + phone1 + ", phone2="
					+ phone2 + ", email=" + email + ", foto=" + foto + ", permission=" + permission + ", createdAt="
					+ createdAt + ", updatedAt=" + updatedAt + ", lastAccess=" + lastAccess + "]";
		} catch(NullPointerException e) {
			return "User [id=" + id + ", name=" + name + "]";
		}
	}
	
}
