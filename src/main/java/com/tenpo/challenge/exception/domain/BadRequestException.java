package com.tenpo.challenge.exception.domain;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends ApiException {

    public BadRequestException(String description, Throwable cause) {
        super(BAD_REQUEST, description, cause);
    }

}
