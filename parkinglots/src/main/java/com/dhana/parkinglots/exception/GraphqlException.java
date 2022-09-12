package com.dhana.parkinglots.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import graphql.language.SourceLocation;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

public class GraphqlException extends  RuntimeException implements GraphQLError {
    public GraphqlException(@NotNull RuntimeException ex){
        super(ex.toString());
    }



    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

    @Override
    public List<Object> getPath() {
        return GraphQLError.super.getPath();
    }

    @Override
    public Map<String, Object> toSpecification() {
        return GraphQLError.super.toSpecification();
    }

    @Override
    public Map<String, Object> getExtensions() {
        return GraphQLError.super.getExtensions();
    }
}
