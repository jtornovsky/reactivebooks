package com.devskiller.services;

import com.devskiller.model.Reader;
import com.devskiller.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
