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

	@ManyToMany
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

	public Reader(int age) {
		this.age = age;
	}

	public void addToFavourites(Book book) {
		favouriteBooks.add(book);
	}

	public void addToFavourites(Genre genre) {
		favouriteGenres.add(genre);
	}

	public void removeFromFavourites(Book book) {
		favouriteBooks.remove(book);
	}

	public void removeFromFavourites(Genre genre) {
		favouriteGenres.remove(genre);
	}

	public Set<Book> getFavouriteBooks() {
		return Sets.newHashSet(favouriteBooks);
	}

	public Set<Genre> getFavouriteGenres() {
		return Sets.newHashSet(favouriteGenres);
	}

	@Override
	public int hashCode() {
		int result = favouriteBooks.hashCode();
		result = 31 * result + favouriteGenres.hashCode();
		result = 31 * result + age;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Reader reader = (Reader) o;

		if (age != reader.age) return false;
		if (!favouriteBooks.equals(reader.favouriteBooks)) return false;
		return favouriteGenres.equals(reader.favouriteGenres);
	}
}
