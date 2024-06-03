package com.devskiller.mapper;

import com.devskiller.dto.AuthorDto;
import com.devskiller.model.Author;

public class AuthorMapper {

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getFirstName(), author.getLastName());
    }

    public static Author toEntity(AuthorDto dto) {
        return new Author(dto.firstName(), dto.lastName());
    }
}

