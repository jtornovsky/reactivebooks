package com.devskiller.controller;

import com.devskiller.model.Book;
import com.devskiller.services.BookSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/books")
public class BookSuggestionController {

    private final BookSuggestionService bookSuggestionService;

    @Autowired
    public BookSuggestionController(BookSuggestionService bookSuggestionService) {
        this.bookSuggestionService = bookSuggestionService;
    }

    @GetMapping("/suggest")
    @ResponseStatus(HttpStatus.OK)
    public Set<String> suggestBooks(@RequestParam Long readerId) {
        return bookSuggestionService.suggestBooks(readerId);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody Book book) {
        // need to create BookDto and get it as a request body param
        bookSuggestionService.addBook(book);
    }
}

