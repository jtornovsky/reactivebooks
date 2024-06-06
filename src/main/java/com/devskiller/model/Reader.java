package com.devskiller.model;

import java.util.Set;

import com.google.common.collect.Sets;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reader")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, unique = true)
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "reader_favourite_books",
			joinColumns = @JoinColumn(name = "reader_id"),
			inverseJoinColumns = @JoinColumn(name = "book_id")
	)
	private Set<Book> favouriteBooks = Sets.newHashSet();

	@ElementCollection(targetClass = Genre.class)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "reader_favourite_genres", joinColumns = @JoinColumn(name = "reader_id"))
	@Column(name = "favourite_genre", nullable = false)
	private Set<Genre> favouriteGenres = Sets.newHashSet();

	@Column(name = "age", nullable = false)
	private int age;

	public Reader(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	@Override
	public int hashCode() {
		int result = favouriteBooks.hashCode();
		result = 31 * result + favouriteGenres.hashCode();
		result = 31 * result + firstName.hashCode();
		result = 31 * result + lastName.hashCode();
		result = 31 * result + age;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Reader reader = (Reader) o;

		if (age != reader.age) return false;
		if (!firstName.equals(reader.firstName)) return false;
		if (!lastName.equals(reader.lastName)) return false;
		if (!favouriteBooks.equals(reader.favouriteBooks)) return false;
		return favouriteGenres.equals(reader.favouriteGenres);
	}
}
