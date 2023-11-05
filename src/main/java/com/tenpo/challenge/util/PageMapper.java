package com.tenpo.challenge.util;

import com.tenpo.challenge.model.PageModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageMapper {

    public <T> PageModel<T> map(Page<T> page) {
        return new PageModel<>(page.getContent(), page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
    }
}
