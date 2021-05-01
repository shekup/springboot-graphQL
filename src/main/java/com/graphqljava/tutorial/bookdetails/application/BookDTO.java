package com.graphqljava.tutorial.bookdetails.application;

import lombok.Data;

@Data
public class BookDTO {

    private String name;
    private int pageCount;
    private String authorFirstName;
    private String authorLastName;
}
