package com.brendonbody.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UpStreamException.class)
    public ProblemDetail handleUpstreamApiException(
            UpStreamException exception
    ) {
        logger.error("Error calling EatClub", exception);
        return ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_GATEWAY,
                "Unable to retrieve deals from the upstream API"
        );
    }

    @ExceptionHandler(InternalException.class)
    public ProblemDetail handleInternalException(
            InternalException exception
    ) {
        logger.error("An unexpected error occurred", exception);
        return ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred"
        );
    }


    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

}
