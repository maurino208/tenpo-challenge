package com.tenpo.challenge.exception;

import com.tenpo.challenge.exception.domain.ApiException;
import org.springframework.stereotype.Component;

@Component
public class ErrorMapper {

    public ErrorModel map(ApiException apiException) {
        return ErrorModel.builder()
                .id(apiException.getId())
                .title(apiException.getTitle())
                .description(apiException.getDescription())
                .httpStatus(apiException.getHttpStatus().value())
                .date(apiException.getDate())
                .build();
    }

}
