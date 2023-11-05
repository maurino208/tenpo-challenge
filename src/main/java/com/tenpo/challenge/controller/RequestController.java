package com.tenpo.challenge.controller;

import com.tenpo.challenge.model.PageModel;
import com.tenpo.challenge.model.RequestHttp;
import com.tenpo.challenge.service.RequestService;
import com.tenpo.challenge.util.PageMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RequestController {

    private final RequestService service;
    private final PageMapper pageMapper;

    @GetMapping("/internal/requests")
    public PageModel<RequestHttp> get(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        return pageMapper.map(service.findAll(page, size));
    }

}
