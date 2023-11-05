package com.tenpo.challenge.filter;

import com.tenpo.challenge.async.AsyncRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Enumeration;

import static java.util.Arrays.copyOf;
import static org.apache.commons.lang3.StringUtils.normalizeSpace;

@Component
@Order(2)
@AllArgsConstructor
public class RequestFilter extends OncePerRequestFilter {

    private final AsyncRequest asyncRequest;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        var responseWrapper = httpServletResponse instanceof ContentCachingResponseWrapper rw ? rw : new ContentCachingResponseWrapper(httpServletResponse);
        var requestWrapper = httpServletRequest instanceof ContentCachingRequestWrapper rq ? rq : new ContentCachingRequestWrapper(httpServletRequest);

        filterChain.doFilter(requestWrapper, responseWrapper);

        request(requestWrapper, responseWrapper);

        responseWrapper.copyBodyToResponse();
    }

    private void request(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper) {
        asyncRequest.request(
                requestWrapper.getRequestURI(),
                requestWrapper.getMethod(),
                requestWrapper.getQueryString(),
                getHeaders(requestWrapper),
                getRequestString(requestWrapper),
                getResponseString(responseWrapper),
                responseWrapper.getStatus()
        );
    }

    private String getHeaders(ContentCachingRequestWrapper requestWrapper) {
        StringBuilder stringBuilder = new StringBuilder();
        Enumeration<String> headerNames = requestWrapper.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            stringBuilder.append(headerName)
                    .append(":")
                    .append(requestWrapper.getHeader(headerName))
                    .append(";");
        }
        return stringBuilder.toString();
    }

    private String getResponseString(ContentCachingResponseWrapper responseWrapper) {
        return getStringFromBytes(responseWrapper.getContentAsByteArray());
    }

    private String getRequestString(ContentCachingRequestWrapper requestWrapper) {
        return getStringFromBytes(requestWrapper.getContentAsByteArray());
    }

    private String getStringFromBytes(byte[] bytes) {
        return normalizeSpace(new String(copyOf(bytes, bytes.length))).trim();
    }

}
