package com.devskiller.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.devskiller.model.Author}
 */
public record AuthorDto(String firstName, String lastName) implements Serializable {
}