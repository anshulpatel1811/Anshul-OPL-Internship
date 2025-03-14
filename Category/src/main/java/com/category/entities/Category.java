package com.category.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "category_tbl")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "cat_id")
	private Long categoryId;
	
	@Column(name = "cat_name",nullable = false,length = 15)
	private String title;
	
	@Column(name = "cat_price",nullable = false)
	private Double price;
	
	@Column(name = "cat_des",nullable = false,length = 50)
	private String description;
	
	@Column(nullable = false,length = 50)
	private double stockQuantity;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	//@Column(name = "created_at", updatable = false)
	private Date createdAt;
}
