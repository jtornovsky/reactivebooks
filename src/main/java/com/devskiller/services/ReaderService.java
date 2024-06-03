package com.devskiller.services;

import com.devskiller.model.Book;
import com.devskiller.model.Genre;
import com.devskiller.model.Reader;
import com.devskiller.repo.ReaderRepository;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public void saveReader(Reader reader) {
        readerRepository.save(reader);
    }

//    public void addToFavourites(Book book) {
//        favouriteBooks.add(book);
//    }
//
//    public void addToFavourites(Genre genre) {
//        favouriteGenres.add(genre);
//    }
//
//    public void removeFromFavourites(Book book) {
//        favouriteBooks.remove(book);
//    }
//
//    public void removeFromFavourites(Genre genre) {
//        favouriteGenres.remove(genre);
//    }
//
//    public Set<Book> getFavouriteBooks() {
//        return Sets.newHashSet(favouriteBooks);
//    }
//
//    public Set<Genre> getFavouriteGenres() {
//        return Sets.newHashSet(favouriteGenres);
//    }
}
