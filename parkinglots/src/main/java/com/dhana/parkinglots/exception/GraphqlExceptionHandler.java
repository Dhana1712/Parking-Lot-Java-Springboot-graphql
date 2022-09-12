package com.dhana.parkinglots.exception;

import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GraphqlExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ThrowableGraphQLError handle(AccessDeniedException e){
        return new ThrowableGraphQLError(e,"You have no permission");
    }
}
