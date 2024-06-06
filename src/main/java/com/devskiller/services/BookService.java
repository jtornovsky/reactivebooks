package com.devskiller.services;

import com.devskiller.model.Book;
import com.devskiller.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void addBook(Book book) {
        bookRepository.saveAndFlush(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> findByTitleAndAuthor(String title, String authorFirstName, String authorLastName) {
        return bookRepository.findBookByTitleAndAndAuthor_FirstNameAndAuthorLastName(title, authorFirstName, authorLastName);
    }
}
