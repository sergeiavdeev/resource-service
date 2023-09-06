package ru.avdeev.resourceservice.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public class ResourceNotFoundException extends ApiException{

    public ResourceNotFoundException(String message) {
        super(HttpResponseStatus.NOT_FOUND, message);
    }
}
