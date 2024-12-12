package com.panvdev.apirest_prueba.modelos;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "discounts") 
public class Disccount {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long discount_id;
	
	private String discount_code;
	private BigDecimal  discount_percentage;
	private  Date start_date;
	private  Date end_date;
	private  BigDecimal min_purchase ;
	private  String description ;
	
	public Disccount() {}

	public long getDiscount_id() {
		return discount_id;
	}

	public void setDiscount_id(long discount_id) {
		this.discount_id = discount_id;
	}

	public String getDiscount_code() {
		return discount_code;
	}

	public void setDiscount_code(String discount_code) {
		this.discount_code = discount_code;
	}

	public BigDecimal getDiscount_percentage() {
		return discount_percentage;
	}

	public void setDiscount_percentage(BigDecimal discount_percentage) {
		this.discount_percentage = discount_percentage;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public BigDecimal getMin_purchase() {
		return min_purchase;
	}

	public void setMin_purchase(BigDecimal min_purchase) {
		this.min_purchase = min_purchase;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
