package com.graphqljava.tutorial.bookdetails.domain;

import com.graphqljava.tutorial.bookdetails.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
