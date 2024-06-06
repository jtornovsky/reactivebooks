package com.devskiller.services;

import com.devskiller.model.Book;
import com.devskiller.model.Genre;
import com.devskiller.model.Reader;
import com.devskiller.repo.ReaderRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public Optional<Reader> getReaderById(Long readerId) {
        return readerRepository.findById(readerId);
    }

    @Transactional(readOnly = true)
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @Transactional
    public void saveReader(Reader reader) {
        readerRepository.saveAndFlush(reader);
    }

    @Transactional()
    public void addToFavourites(Reader reader, Book book) {
        reader.getFavouriteBooks().add(book);
    }

    @Transactional()
    public void addToFavourites(Reader reader, Genre genre) {
        reader.getFavouriteGenres().add(genre);
    }

    @Transactional()
    public void removeFromFavourites(Reader reader, Book book) {
        reader.getFavouriteBooks().remove(book);
    }

    @Transactional()
    public void removeFromFavourites(Reader reader, Genre genre) {
        reader.getFavouriteGenres().remove(genre);
    }

    @Transactional(readOnly = true)
    public Set<Book> getFavouriteBooks(Reader reader) {
        return Sets.newHashSet(reader.getFavouriteBooks());
    }

    @Transactional(readOnly = true)
    public Set<Genre> getFavouriteGenres(Reader reader) {
        return Sets.newHashSet(reader.getFavouriteGenres());
    }
}
