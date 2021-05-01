package com.graphqljava.tutorial.bookdetails;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

public class GraphQLProvider {

    private GraphQL graphQL;

    GraphQLDataFetchers graphQLDataFetchers;

    public GraphQL graphQL() {
        return graphQL;
    }

    /**
     * We use Guava Resources to read the file from our classpath,
     * then create a GraphQLSchema and GraphQL instance.
     * This GraphQL instance is exposed as a Spring Bean via the graphQL() method annotated with @Bean.
     * The GraphQL Java Spring adapter will use that GraphQL instance to make our schema available
     * via HTTP on the default url /graphql.
     *
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    /**
     * TypeDefinitionRegistry is the parsed version of our schema file.
     * SchemaGenerator combines the TypeDefinitionRegistry with RuntimeWiring to actually make the GraphQLSchema
     * @param sdl
     * @return
     */
    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    /**
     * buildWiring uses the graphQLDataFetchers bean to actually register two DataFetchers:
     * One to retrieve a book with a specific ID
     * One to get the author for a specific book.
     *
     */
    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
                .type(newTypeWiring("Book")
                        .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
                .build();
    }

}
