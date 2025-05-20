package com.interview.practice.graphqlspringboot.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof ResourceNotFoundException) {
            return GraphqlErrorBuilder.newError()
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .extensions(Map.of("type", "NOT_FOUND"))
                .build();
        }

        return GraphqlErrorBuilder.newError()
            .message("Internal Server Error")
            .path(env.getExecutionStepInfo()
                .getPath())
            .location(env.getField()
                    .getSourceLocation())
                .extensions(Map.of("type", "INTERNAL_ERROR"))
                .build();
    }
} 