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

import java.util.Set;

import static com.devskiller.model.Genre.*;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BookSuggestionServiceWithSpringBootTests {

    @Autowired
    private BookSuggestionService bookSuggestionService;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderService readerService;

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
    private Reader reader1 = new Reader(25);
    private Reader reader2 = new Reader(25);
    private Reader reader3 = new Reader(randomAge2);

    @Test
    @Transactional
    public void testSuggestBooks() {

        reader1.getFavouriteBooks().add(book1);
        reader1.getFavouriteBooks().add(book2);

        reader2.getFavouriteBooks().add(book1);
        reader2.getFavouriteBooks().add(book2);

        readerService.saveReader(reader1);
        readerService.saveReader(reader2);
        readerService.saveReader(reader3);

        bookSuggestionService.addBook(book1);
        bookSuggestionService.addBook(book2);
        bookSuggestionService.addBook(book3);
        bookSuggestionService.addBook(book4);
        bookSuggestionService.addBook(book5);
        bookSuggestionService.addBook(book6);

        assertEquals(3, readerRepository.findAll().size());
        assertEquals(6, bookRepository.findAll().size());

        // Test suggestBooks method - STILL FAILS, had no time to complete
        Set<String> suggestions = bookSuggestionService.suggestBooks(reader1.getId());
        assertTrue(suggestions.contains(book1.getTitle()));
        assertTrue(suggestions.contains(book2.getTitle()));
    }
}
