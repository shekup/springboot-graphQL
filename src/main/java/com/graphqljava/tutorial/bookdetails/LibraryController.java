package com.graphqljava.tutorial.bookdetails;

import com.graphqljava.tutorial.bookdetails.application.BookDTO;
import com.graphqljava.tutorial.bookdetails.application.LibraryService;
import com.graphqljava.tutorial.bookdetails.domain.Author;
import com.graphqljava.tutorial.bookdetails.domain.AuthorRepository;
import com.graphqljava.tutorial.bookdetails.domain.Book;
import com.graphqljava.tutorial.bookdetails.domain.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryController {

    private final LibraryService libraryService;

    LibraryController (LibraryService libraryService){
        this.libraryService = libraryService;
    }

    @RequestMapping("/")
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping("/books")
    List<BookDTO> all(){
        List<BookDTO> bookDTOLst = libraryService.all();
        return bookDTOLst;
    }

    @PostMapping("/books")
    long addBook(@RequestBody BookDTO bookDTO){
        return libraryService.addBook(bookDTO);
    }
}
