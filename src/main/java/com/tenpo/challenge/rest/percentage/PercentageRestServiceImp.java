package com.tenpo.challenge.rest.percentage;

import com.tenpo.challenge.rest.RestService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.tenpo.challenge.util.Constant.PROD_PROFILE;

@Service
@AllArgsConstructor
@Profile(PROD_PROFILE)
public class PercentageRestServiceImp implements PercentageRestService {

    private final String uri = "http://some-domain/percentages/num1=%.2f&num2=%.2f";
    private RestService restService;

    @Override
    public BigDecimal get(BigDecimal num1, BigDecimal num2) {
        return restService.get(String.format(uri, num1, num2), BigDecimal.class);
    }

}
