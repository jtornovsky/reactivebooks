package com.devskiller.repo;

import com.devskiller.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitleAndAndAuthor_FirstNameAndAuthorLastName(String title, String authorFirstName, String authorLastName);
}
