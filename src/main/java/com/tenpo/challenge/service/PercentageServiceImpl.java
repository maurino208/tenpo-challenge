package com.tenpo.challenge.service;

import com.tenpo.challenge.rest.percentage.PercentageRestService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.tenpo.challenge.util.Constant.PERCENTAGE_CACHE_NAME;

@Service
@AllArgsConstructor
public class PercentageServiceImpl implements PercentageService {

    private final PercentageRestService percentageRestService;

    @Cacheable(cacheNames = PERCENTAGE_CACHE_NAME)
    public BigDecimal get(BigDecimal num1, BigDecimal num2) {
        return percentageRestService.get(num1, num2);
    }

}
