package com.panvdev.apirest_prueba.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories") 
public class Category {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long category_id;
	private String category;
	
	public Category(){}

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public String getCategoty() {
		return category;
	}

	public void setCategoty(String categoty) {
		this.category = categoty;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
