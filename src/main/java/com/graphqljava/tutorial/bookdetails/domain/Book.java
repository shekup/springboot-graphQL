package com.graphqljava.tutorial.bookdetails.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Data
@EqualsAndHashCode
@Entity
public class Book {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pageCount", nullable = false)
    private int pageCount;

    @Column(name = "authorId", nullable = false)
    private long authorId;
}
