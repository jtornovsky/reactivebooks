package com.devskiller.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, unique = true)
	private Long id;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "isbn")
	private String isbn;

	@Enumerated(EnumType.STRING)
	@Column(name = "genre", nullable = false)
	private Genre genre;

	@Column(name = "rating", nullable = false)
	private int rating;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id")
	private Set<Reader> readers = new HashSet<>();

	public Book(Author author, String title, String isbn, Genre genre) {
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.genre = genre;
	}

	public Book(Author author, String title, String isbn, Genre genre, int rating) {
		validate(rating);
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.genre = genre;
		this.rating = rating;
	}

	private void validate(int rating) {
		if (rating > 5 || rating < 1) {
			throw new IllegalArgumentException();
		}
	}

	public void setRating(int rating) {
		validate(rating);
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		int result = author != null ? author.hashCode() : 0;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
		result = 31 * result + (genre != null ? genre.hashCode() : 0);
		result = 31 * result + rating;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Book book = (Book) o;

		if (rating != book.rating) return false;
		if (!Objects.equals(author, book.author)) return false;
		if (!Objects.equals(title, book.title)) return false;
		if (!Objects.equals(isbn, book.isbn)) return false;
		return genre == book.genre;
	}
}
