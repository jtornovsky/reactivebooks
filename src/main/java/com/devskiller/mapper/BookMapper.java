package com.devskiller.mapper;

import com.devskiller.dto.BookDto;
import com.devskiller.model.Author;
import com.devskiller.model.Book;
import com.devskiller.services.AuthorService;
import com.devskiller.services.BookService;
import jakarta.persistence.EntityNotFoundException;

public class BookMapper {

    public static BookDto toDto(Book book) {
        return new BookDto(
                AuthorMapper.toDto(book.getAuthor()),
                book.getTitle(),
                book.getIsbn(),
                book.getGenre(),
                book.getRating()
        );
    }

    public static Book toEntity(BookDto dto, BookService bookService, AuthorService authorService) {
        Author author = authorService.findByFirstAndLastName(dto.author().firstName(), dto.author().lastName())
                .orElseThrow(() -> new EntityNotFoundException("Author not found: " + dto.author().firstName() + " " + dto.author().lastName()));
        return bookService.findByTitleAndAuthor(dto.title(), author.getFirstName(), author.getLastName())
                .orElseGet(() -> new Book(author, dto.title(), dto.isbn(), dto.genre(), dto.rating()));
    }
}


