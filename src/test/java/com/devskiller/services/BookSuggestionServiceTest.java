package com.devskiller.services;

import com.devskiller.model.Author;
import com.devskiller.model.Book;
import com.devskiller.model.Reader;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static com.devskiller.model.Genre.DRAMA;
import static com.devskiller.model.Genre.HORROR;
import static com.devskiller.model.Genre.ROMANTIC;
import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;

public class BookSuggestionServiceTest {

	private Author author1 = new Author(randomAlphabetic(8), randomAlphabetic(10));
	private Author author2 = new Author(randomAlphabetic(8), randomAlphabetic(10));
	private Author author3 = new Author(randomAlphabetic(8), randomAlphabetic(10));
	private Author author4 = new Author(randomAlphabetic(8), randomAlphabetic(10));
	private Author author5 = new Author(randomAlphabetic(8), randomAlphabetic(10));
	private Book book1 = new Book(author1, randomAlphabetic(15), randomAlphabetic(10), HORROR, 5);
	private Book book2 = new Book(author1, randomAlphabetic(15), randomAlphabetic(10), HORROR, 4);
	private Book book3 = new Book(author2, randomAlphabetic(15), randomAlphabetic(10), HORROR, 3);
	private Book book4 = new Book(author3, randomAlphabetic(15), randomAlphabetic(10), ROMANTIC, 5);
	private Book book5 = new Book(author4, randomAlphabetic(15), randomAlphabetic(10), HORROR, 5);
	private Book book6 = new Book(author5, randomAlphabetic(15), randomAlphabetic(10), DRAMA, 5);
	private int randomAge1 = nextInt(0, 120);
	private int randomAge2 = nextInt(0, 120);
	private Reader reader1 = new Reader(randomAge1);
	private Reader reader2 = new Reader(randomAge1);
	private Reader reader3 = new Reader(randomAge2);
	private BookSuggestionService suggestionService;

	@Before
	public void setUp() {
		reader1.addToFavourites(HORROR);
		reader1.addToFavourites(ROMANTIC);
		reader2.addToFavourites(book2);
		reader2.addToFavourites(book1);
		reader2.addToFavourites(book3);
		reader2.addToFavourites(book4);
		reader3.addToFavourites(book5);
		Set<Book> books = newHashSet(book1, book2, book3, book4, book5, book6);
		Set<Reader> readers = newHashSet(reader1, reader2, reader3);
		suggestionService = new BookSuggestionService(books, readers);
	}

	@Test
	public void shouldSuggestBookTitlesWithCorrectRating() {
		// when:
		Set<String> suggestedBooks = suggestionService.suggestBooks(reader1, 5);

		// then:
		assertThat(suggestedBooks).isEqualTo(newHashSet(book1.getTitle(), book4.getTitle()));
	}

	@Test
	public void shouldSuggestBookTitlesWithDefaultRatingOfFourOrHigher() {
		// when:
		Set<String> suggestedBooks = suggestionService.suggestBooks(reader1);

		// then:
		assertThat(suggestedBooks).isEqualTo(newHashSet(book1.getTitle(), book2.getTitle(),
				book4.getTitle()));
	}

	@Test
	public void shouldOnlySuggestBookTitlesOfGivenAuthor() {
		// when:
		Set<String> suggestedBooks = suggestionService.suggestBooks(reader1, author1);

		// then:
		assertThat(suggestedBooks).isEqualTo(newHashSet(book1.getTitle(), book2.getTitle()));
	}
}