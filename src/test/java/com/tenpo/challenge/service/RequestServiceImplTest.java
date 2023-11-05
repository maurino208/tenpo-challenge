package com.tenpo.challenge.service;

import com.tenpo.challenge.model.RequestHttp;
import com.tenpo.challenge.repository.RequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestServiceImplTest {

    @Mock
    private RequestRepository repository;

    @InjectMocks
    private RequestServiceImpl service;

    @Test
    public void testSave_OK() {
        RequestHttp request = buildRequest(randomUUID());

        service.save(request);

        verify(repository).save(request);
    }


    @Test
    public void testFindAll_OK() {
        int pageNumber = 0;
        int pageSize = 10;

        var requestList = asList(
                buildRequest(randomUUID()),
                buildRequest(randomUUID())
        );

        var transactionsPage = new PageImpl<>(requestList);
        var pageable = PageRequest.of(pageNumber, pageSize);

        when(repository.findAll(pageable))
                .thenReturn(transactionsPage);

        var retrieved = service.findAll(0, 10);

        assertThat(retrieved).isEqualTo(transactionsPage);

        verify(repository).findAll(pageable);
    }

    private RequestHttp buildRequest(UUID id) {
        return RequestHttp.builder().id(id).build();
    }

}
