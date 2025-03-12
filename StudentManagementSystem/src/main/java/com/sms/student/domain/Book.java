package com.sms.student.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "book_tbl")
public class Book {

	@Id
	@Column(name = "book_id")
	private String bid;
	
	@Column(name = "book_name",length = 50)
	private String name;
	
	@Column(name = "book_des",length = 300)
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Author> authors;
}
