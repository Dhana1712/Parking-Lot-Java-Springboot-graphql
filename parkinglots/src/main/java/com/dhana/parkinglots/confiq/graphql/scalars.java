package com.dhana.parkinglots.confiq.graphql;

import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class scalars {
    @Bean
    public GraphQLScalarType Date() {
        return ExtendedScalars.Date;
    }

    @Bean
    public GraphQLScalarType Upload(){
        return ApolloScalars.Upload;
    }

}
