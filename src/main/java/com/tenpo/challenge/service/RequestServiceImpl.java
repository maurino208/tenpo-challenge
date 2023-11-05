package com.tenpo.challenge.service;

import com.tenpo.challenge.model.RequestHttp;
import com.tenpo.challenge.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;

    public void save(RequestHttp request) {
        repository.save(request);
    }

    public Page<RequestHttp> findAll(int pageNumber, int pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize));
    }
}
