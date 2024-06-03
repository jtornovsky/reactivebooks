package com.devskiller.dto;

import com.devskiller.model.Genre;

import java.io.Serializable;

/**
 * DTO for {@link com.devskiller.model.Book}
 */
public record BookDto(AuthorDto author, String title, String isbn, Genre genre,
                      int rating) implements Serializable {
}