package com.tenpo.challenge.rest.percentage;

import org.springframework.retry.annotation.Retryable;

import java.math.BigDecimal;

public interface PercentageRestService {
    @Retryable
    BigDecimal get(BigDecimal num1, BigDecimal num2);

}
