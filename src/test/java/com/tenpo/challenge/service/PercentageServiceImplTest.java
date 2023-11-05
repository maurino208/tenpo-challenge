package com.tenpo.challenge.service;

import com.tenpo.challenge.rest.percentage.PercentageRestServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PercentageServiceImplTest {

    @Mock
    private PercentageRestServiceImp percentageRestService;

    @InjectMocks
    private PercentageServiceImpl service;

    @Test
    void testGet_OK() {
        var num1 = new BigDecimal("123.33");
        var num2 = new BigDecimal("5.5555");
        var expected = new BigDecimal("1.1");

        when(percentageRestService.get(num1, num2))
                .thenReturn(expected);

        var retrieved = service.get(num1, num2);

        assertThat(retrieved).isEqualTo(expected);

        verify(percentageRestService).get(num1, num2);
    }

}
