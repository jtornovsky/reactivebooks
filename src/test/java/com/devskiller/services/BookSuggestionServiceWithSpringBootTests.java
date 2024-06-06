package com.devskiller.services;

import com.devskiller.model.Author;
import com.devskiller.model.Book;
import com.devskiller.model.Reader;
import com.devskiller.repo.BookRepository;
import com.devskiller.repo.ReaderRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Set;

import static com.devskiller.model.Genre.*;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
public class BookSuggestionServiceWithSpringBootTests {

    @Container
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.28")
            .withDatabaseName("reactivebookstestdb")
            .withUsername("test")
            .withPassword("test");

    private final BookSuggestionService bookSuggestionService;
    private final BookService bookService;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;
    private final ReaderService readerService;

    @Autowired
    public BookSuggestionServiceWithSpringBootTests(BookSuggestionService bookSuggestionService,
                                                    BookService bookService, ReaderRepository readerRepository,
                                                    BookRepository bookRepository, ReaderService readerService) {
        this.bookSuggestionService = bookSuggestionService;
        this.bookService = bookService;
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
        this.readerService = readerService;
    }

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
    private Reader reader1 = new Reader(randomAlphabetic(8), randomAlphabetic(10), 25);
    private Reader reader2 = new Reader(randomAlphabetic(8), randomAlphabetic(10), 25);
    private Reader reader3 = new Reader(randomAlphabetic(8), randomAlphabetic(10), nextInt(0, 120));

    @Test
    @Transactional
    public void testSuggestBooks() {

        reader1.getFavouriteBooks().add(book1);
        reader1.getFavouriteBooks().add(book2);

        readerService.addToFavourites(reader1, HORROR);
        readerService.addToFavourites(reader1, ROMANTIC);

        reader2.getFavouriteBooks().add(book1);
        reader2.getFavouriteBooks().add(book2);

        readerService.saveReader(reader1);
        readerService.saveReader(reader2);
        readerService.saveReader(reader3);

        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);
        bookService.addBook(book5);
        bookService.addBook(book6);

        assertEquals(3, readerService.getAllReaders().size());
        assertEquals(6, bookService.getAllBooks().size());

        Set<String> suggestions = bookSuggestionService.suggestBooks(reader1.getId());
        assertTrue(suggestions.contains(book1.getTitle()));
        assertTrue(suggestions.contains(book2.getTitle()));
    }
}
