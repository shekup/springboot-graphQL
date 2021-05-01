# springboot-graphQL
I tried implementing https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/
but found later that it is outdated and it didnt work for me. 

I created this project which works and is simple too as it does not require GraphQLProvider and GraphQLDatafetchers.  Instead uses a simple Query class that does everything in background.  In order to use GraphQL, only two things are neeeded:
1. "schema.graphqls" file in resources to define the queries
2. A class that implements "GraphQLQueryResolver".  In this project it is named as "Query" class. 
In "schema.graphqls" the queries are defined and inside the "Query" class has implementation of the queries, that is, how queries will fetch data. 
GraphQL can be used to create data as well but in this project, I have only used it to query. 

The project is a hypothetical basically basic library with two entities: 'book" and 'author'.  A rest controller class creates the book and author inside in-memory database.  
The book & author can then be queried using GraphQL.  

To test/run GraphQL queries.  Run the project and hit 'http://localhost:8080/graphiql' in browser.  Execute the query, such as for querying a book by id send query:

query {
  book(id: 2) 
  {
    id, 
    name, 
    pageCount,
    authorId
}
}

To query an author by id, use query:

query {
  author(id: 1) 
  {
    id, 
    firstName,
    lastName
}
}
