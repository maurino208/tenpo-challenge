package com.tenpo.challenge.controller;

import com.tenpo.challenge.dto.RequestDto;
import com.tenpo.challenge.dto.ResponseDto;
import com.tenpo.challenge.service.CalculateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class CalculatorController {

    private final CalculateService calculateService;

    @PostMapping("/api/calculate")
    public ResponseDto calculate(@RequestBody @Valid RequestDto req) {
        return buildResponse(calculateService.calculateValue(req.getNum1(), req.getNum2()));
    }

    private ResponseDto buildResponse(BigDecimal result) {
        return ResponseDto.builder()
                .result(result)
                .build();
    }
}

