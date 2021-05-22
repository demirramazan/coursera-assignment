package com.coursera.librarian.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }
    private final Logger logger = LoggerFactory.getLogger(RestControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(Exception exception) {
        logger.error(exception.toString());
        return getExceptionResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> exceptionNotFoundHandler(NotFoundException exception) {
        logger.error(exception.toString());
        return getExceptionResponse(exception, HttpStatus.NOT_FOUND);
    }
//
    @ExceptionHandler(AlreadyEntityException.class)
    public ResponseEntity<ExceptionResponse> exceptionConflictHandler(Exception exception) {
        logger.error(exception.toString());
        return getExceptionResponse(exception, HttpStatus.CONFLICT);
    }

    private ResponseEntity<ExceptionResponse> getExceptionResponse(Exception exception, HttpStatus status) {
        ExceptionResponse exceptionResult = new ExceptionResponse();
        exceptionResult.setResultCode(status.value());
        exceptionResult.setResultMessage(exception.getMessage());
        exceptionResult.setResultMessageDetail(exception.toString());
        return new ResponseEntity<>(exceptionResult, status);
    }
}
