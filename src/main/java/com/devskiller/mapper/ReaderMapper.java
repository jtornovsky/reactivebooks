package com.devskiller.mapper;

import com.devskiller.dto.BookDto;
import com.devskiller.dto.ReaderDto;
import com.devskiller.model.Reader;
import com.devskiller.services.AuthorService;
import com.devskiller.services.BookService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ReaderMapper {

    public static ReaderDto toDto(Reader reader) {
        Set<BookDto> favouriteBookDtos = reader.getFavouriteBooks().stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toSet());
        return new ReaderDto(favouriteBookDtos, new HashSet<>(reader.getFavouriteGenres()), reader.getAge());
    }

    public static Reader toEntity(ReaderDto dto, BookService bookService, AuthorService authorService) {
        Reader reader = new Reader();
        reader.setFavouriteBooks(dto.favouriteBooks().stream()
                .map(bookDto -> BookMapper.toEntity(bookDto, bookService, authorService))
                .collect(Collectors.toSet()));
        reader.setFavouriteGenres(new HashSet<>(dto.favouriteGenres()));
        reader.setAge(dto.age());
        return reader;
    }
}


