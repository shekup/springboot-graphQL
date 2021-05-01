package com.graphqljava.tutorial.bookdetails.application;

import com.graphqljava.tutorial.bookdetails.domain.Author;
import com.graphqljava.tutorial.bookdetails.domain.AuthorRepository;
import com.graphqljava.tutorial.bookdetails.domain.Book;
import com.graphqljava.tutorial.bookdetails.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public long addBook (BookDTO bookDTO){

        Author author = new Author();
        author.setFirstName(bookDTO.getAuthorFirstName());
        author.setLastName(bookDTO.getAuthorLastName());
        Author authorCreated = authorRepository.save(author);

        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setAuthorId(authorCreated.getId());
        book.setPageCount(100);
        return bookRepository.save(book).getId();

    }

    public List<BookDTO> all(){
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOLst = new ArrayList<>();
        for(Book book: books){
            BookDTO bookDTO = new BookDTO();
            bookDTO.setName(book.getName());
            bookDTO.setPageCount(book.getPageCount());
            long authorId = book.getAuthorId();
            Author author = authorRepository.getOne(authorId);
            bookDTO.setAuthorFirstName(author.getFirstName());
            bookDTO.setAuthorLastName(author.getLastName());
            bookDTOLst.add(bookDTO);
        }
        return bookDTOLst;
    }


}
