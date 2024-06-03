package com.devskiller.dto;

import com.devskiller.model.Genre;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.devskiller.model.Reader}
 */
public record ReaderDto(Set<BookDto> favouriteBooks, Set<Genre> favouriteGenres, int age) implements Serializable {
}