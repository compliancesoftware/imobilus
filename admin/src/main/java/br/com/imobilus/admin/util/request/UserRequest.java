package br.com.imobilus.admin.util.request;

import java.util.Arrays;
import java.util.Calendar;

import br.com.imobilus.admin.model.Permission;
import br.com.imobilus.admin.model.User;

public class UserRequest {
	private Long id;
	
	private String name;
	
	private String password;
	
	private String phone1;
	
	private String phone2;
	
	private String email;
	
	private byte[] foto;
	
	private Permission permission;
	
	private Calendar createdAt;
	
	private Calendar updatedAt;
	
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
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

	public User toUser() {
		User user = new User();
		user.setId(this.id);
		user.setName(this.name);
		user.setPassword(this.password);
		user.setPhone1(this.phone1);
		user.setPhone2(this.phone2);
		user.setEmail(this.email);
		user.setFoto(this.foto);
		user.setPermission(this.permission);
		user.setCreatedAt(this.createdAt);
		user.setUpdatedAt(this.updatedAt);
		user.setLastAccess(this.lastAccess);
		
		return user;
	}
	
	@Override
	public String toString() {
		try {
			return "UserRequest [id=" + id + ", name=" + name + ", phone1=" + phone1
					+ ", phone2=" + phone2 + ", email=" + email + ", foto=" + Arrays.toString(foto) + ", permission="
					+ permission + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", lastAccess=" + lastAccess
					+ "]";
		} catch(NullPointerException e) {
			return "UserRequest [id=" + id + ", name=" + name + "]";
		}
	}
}
