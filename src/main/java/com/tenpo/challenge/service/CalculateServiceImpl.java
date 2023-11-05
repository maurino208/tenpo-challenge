package com.tenpo.challenge.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.HALF_UP;

@Service
@AllArgsConstructor
public class CalculateServiceImpl implements CalculateService {

    private final PercentageService percentageService;
    private final Integer TWO = 2;
    @Override
    public BigDecimal calculateValue(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2)
                .multiply(getMultiplicand(num1, num2))
                .setScale(TWO, HALF_UP);
    }

    private BigDecimal getMultiplicand(BigDecimal num1, BigDecimal num2) {
        return percentageService.get(num1, num2).movePointLeft(TWO).add(ONE);
    }
}
