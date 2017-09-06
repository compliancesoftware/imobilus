package br.com.douglasfernandes.dataservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mailing")
public class Mailing {
	
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "chave",nullable = false, unique = true)
	private String key;
	@Column(name = "valor",nullable = false)
	private String value;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	
	@Override
	public String toString() {
		return "Mailing [id=" + id + ", key=" + key + ", value=" + value + "]";
	}
}
