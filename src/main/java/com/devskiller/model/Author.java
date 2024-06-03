package com.devskiller.model;

import com.google.common.collect.Sets;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, unique = true)
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id")
	private Set<Book> books = Sets.newHashSet();

	public Author(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
