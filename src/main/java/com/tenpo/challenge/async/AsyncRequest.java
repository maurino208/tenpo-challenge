package com.tenpo.challenge.async;

import com.tenpo.challenge.model.RequestHttp;
import com.tenpo.challenge.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@EnableAsync
public class AsyncRequest {

    private final RequestService service;

    @Async
    public void request(String uri, String method, String queryString, String headers, String request, String response, int httpStatus) {
        service.save(buildTrace(uri, method, queryString, headers, request, response, httpStatus));
    }

    private RequestHttp buildTrace(String uri, String method, String queryString, String headers, String request, String response, int httpStatus) {
        return RequestHttp.builder()
                .uri(uri)
                .method(method)
                .queryString(queryString)
                .headers(headers)
                .request(request)
                .response(response)
                .httpStatus(httpStatus)
                .build();
    }
}
