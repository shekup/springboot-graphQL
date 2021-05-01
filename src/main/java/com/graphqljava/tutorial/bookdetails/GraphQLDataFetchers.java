package com.graphqljava.tutorial.bookdetails;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GraphQLDataFetchers {

    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    "pageCount", "635",
                    "authorId", "author-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3")
    );

    private static List<Map<String, String>> authors = Arrays.asList(
            ImmutableMap.of("id", "author-1",
                    "firstName", "Joanne",
                    "lastName", "Rowling"),
            ImmutableMap.of("id", "author-2",
                    "firstName", "Herman",
                    "lastName", "Melville"),
            ImmutableMap.of("id", "author-3",
                    "firstName", "Anne",
                    "lastName", "Rice")
    );

    /**
     * returns a DataFetcher implementation which takes a DataFetcherEnvironment and returns a book.
     * In our case this means we need to get the id argument from the bookById field and
     * find the book with this specific id. If we can’t find it, we just return null.
     *
     * The “id” in String bookId = dataFetchingEnvironment.getArgument("id");
     * is the “id” from the bookById query field in the schema
     *
     */
    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    /**
     * returns a DataFetcher for getting the author for a specific book.
     * Compared to the previously described book DataFetcher, we don’t have an argument,
     * but we have a book instance.
     * The result of the DataFetcher from the parent field is made available via getSource.
     * This is an important concept to understand:
     * the DataFetcher for each field in GraphQL are called in a top-down fashion
     * and the parent’s result is the source property of the child DataFetcherEnvironment.
     *
     * We then use the previously fetched book to get the authorId and
     * look for that specific author in the same way we look for a specific book.
     *
     */
    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String,String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");
            return authors
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }
}