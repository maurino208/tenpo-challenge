package com.tenpo.challenge.exception;

import com.tenpo.challenge.exception.domain.ApiException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Optional;

import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.resolve;
import static org.springframework.http.HttpStatusCode.valueOf;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class ExceptionHandling {

    private final ExceptionFactory exceptionFactory;
    private final ErrorMapper errorMapper;

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorModel> handle(NoHandlerFoundException e) {
        return handleResponse(exceptionFactory.buildNoMappingFound(e));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorModel> handle(ApiException e) {
        return handleResponse(e);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorModel> handle(BindException e) {
        var description = String.format("Error with Object %s. %s", e.getObjectName(), getCollapsedBindingErrors(e));
        return handleResponse(exceptionFactory.buildBadRequest(description, e));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorModel> handle(HttpMessageNotReadableException e) {
        return handleResponse(exceptionFactory.buildBadRequest(e.getMessage(), e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> handle(Exception e) {
        return handleResponse(getApiException(e));
    }

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ErrorModel> handle(RestClientResponseException e) {
        String description = String.format("External API replied with status %s and body %s", e.getStatusText(), e.getResponseBodyAsString());
        return handleResponse(exceptionFactory.buildApiException(getHttpStatus(e.getStatusCode()), description, e));
    }

    private static String getCollapsedBindingErrors(BindException e) {
        return e.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> String.format("Field %s: %s", error.getField(), error.getDefaultMessage()))
                .collect(joining(", "));
    }

    private HttpStatus getHttpStatus(HttpStatusCode statusCode) {
        return Optional.ofNullable(resolve(statusCode.value()))
                .orElse(INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorModel> handleResponse(ApiException apiException) {
        log.error(apiException.getId(), apiException);
        return new ResponseEntity<>(errorMapper.map(apiException), valueOf(apiException.getHttpStatus().value()));
    }

    private ApiException getApiException(Exception e) {
        return e.getCause() instanceof ApiException ae? ae : exceptionFactory.buildInternalServerError(e);
    }

}
