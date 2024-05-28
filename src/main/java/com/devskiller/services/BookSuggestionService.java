package com.devskiller.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.devskiller.model.Author;
import com.devskiller.model.Book;
import com.devskiller.model.Reader;
import com.devskiller.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookSuggestionService {

	private final BookRepository bookRepository;
	private final ReaderService readerService;
	private Set<Book> books;
	private Set<Reader> readers;

	@Autowired
	public BookSuggestionService(BookRepository bookRepository, ReaderService readerService,
								 Set<Book> books, Set<Reader> readers) {
		this.bookRepository = bookRepository;
		this.readerService = readerService;
	}

	public BookSuggestionService(Set<Book> books, Set<Reader> readers) {
		this.bookRepository = null;
		this.readerService = null;
		this.books = books;
		this.readers = readers;
	}

	public Set<String> suggestBooks(Long readerId) {
		Optional<Reader> readerOpt = readerService.getReaderById(readerId);

		// Reload books and readers from the persistence layer
		books = new HashSet<>(bookRepository.findAll());
		readers = new HashSet<>(readerService.getAllReaders());

		// Just to show working with persistence layer, as it might be also retrieved from the readers set
		Reader reader = readerOpt.orElseThrow(() -> new RuntimeException("Reader with id " + readerId + " not found."));

		return suggestBooks(reader, books, readers, 4, null, ComparisonType.GREATER_THAN_OR_EQUAL);
	}

	public void addBook(Book book) {
		bookRepository.save(book);
	}

	public Set<String> suggestBooks(Reader reader) {
		return suggestBooks(reader, books, readers, 4, null, ComparisonType.GREATER_THAN_OR_EQUAL);
	}

	Set<String> suggestBooks(Reader reader, int rating) {
		return suggestBooks(reader, books, readers, rating, null, ComparisonType.EQUAL);
	}

	Set<String> suggestBooks(Reader reader, Author author) {
		return suggestBooks(reader, books, readers, 4, author, ComparisonType.GREATER_THAN_OR_EQUAL);
	}

	private Set<String> suggestBooks(Reader reader, Set<Book> books, Set<Reader> readers, int rating, Author author, ComparisonType comparisonType) {
		Set<String> suggestedBooksForReader = new HashSet<>();

		// Get favourite books of other readers with the same age
		Set<String> otherReadersFavouriteBooks = getOtherReadersFavouriteBooks(reader, readers);

		// Filter books based on rating, genre, author (if needed) and favourite books list
		for (Book book : books) {
			if (matchesComparison(book.getRating(), rating, comparisonType) &&
					reader.getFavouriteGenres().contains(book.getGenre()) &&
					(author == null || author.equals(book.getAuthor())) &&
					otherReadersFavouriteBooks.contains(book.getTitle())) {

				suggestedBooksForReader.add(book.getTitle());
			}
		}

		return suggestedBooksForReader;
	}

	private Set<String> getOtherReadersFavouriteBooks(Reader reader, Set<Reader> readers) {

		Set<String> otherReadersFavouriteBooks = new HashSet<>();
		for (Reader otherReader : readers) {	// might be improved by: readerService.getReadersByAge(reader.getAge()), to improve the 'if' below
			if (otherReader.getAge() == reader.getAge() && otherReader != reader) {
				for (Book favoriteBook : otherReader.getFavouriteBooks()) {
					otherReadersFavouriteBooks.add(favoriteBook.getTitle());
				}
			}
		}
		return otherReadersFavouriteBooks;
	}

	private boolean matchesComparison(int bookRating, int rating, ComparisonType comparisonType) {
		switch (comparisonType) {
			case LESS_THAN:
				return bookRating < rating;
			case GREATER_THAN:
				return bookRating > rating;
			case EQUAL:
				return bookRating == rating;
			case LESS_THAN_OR_EQUAL:
				return bookRating <= rating;
			case GREATER_THAN_OR_EQUAL:
				return bookRating >= rating;
			default:
				throw new IllegalArgumentException("Unknown ComparisonType: " + comparisonType);
		}
	}
}
