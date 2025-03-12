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
@Table(name = "author_tbl")
public class Author {

	@Id
	@Column(name = "author_id")
	private String aid;
	
	@Column(name = "author_name",length = 50)
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	//@JoinTable(name = "Author_book", joinColumns = @JoinColum(name = "author_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<Book> books;
	
}
