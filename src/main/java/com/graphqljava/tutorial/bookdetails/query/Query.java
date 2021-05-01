package com.graphqljava.tutorial.bookdetails.query;

import com.graphqljava.tutorial.bookdetails.domain.Author;
import com.graphqljava.tutorial.bookdetails.domain.AuthorRepository;
import com.graphqljava.tutorial.bookdetails.domain.Book;
import com.graphqljava.tutorial.bookdetails.domain.BookRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    AuthorRepository authorRepo;

    @Autowired
    BookRepository bookRepo;

    public Author getAuthor(Long id) {
        return authorRepo.findById(id).orElse(null);
    }

    public Book getBook(Long id) {
        return bookRepo.findById(id).orElse(null);
    }
}
