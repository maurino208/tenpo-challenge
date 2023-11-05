package com.tenpo.challenge.rest;

public interface RestService {

    <T> T get(String url, Class<T> clazz);
}
