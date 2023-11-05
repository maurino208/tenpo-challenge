package com.tenpo.challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {
    private String id;
    private String title;
    private String description;
    private int httpStatus;
    private OffsetDateTime date;
}
