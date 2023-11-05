package com.tenpo.challenge.service;


import com.tenpo.challenge.model.RequestHttp;
import org.springframework.data.domain.Page;

public interface RequestService {

    void save(RequestHttp request);

    Page<RequestHttp> findAll(int pageNumber, int pageSize);
}
