package ru.avdeev.resourceservice.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public class ApiException extends RuntimeException {

    public HttpResponseStatus getHttpStatus() {
        return httpStatus;
    }

    protected HttpResponseStatus httpStatus;

    public ApiException(HttpResponseStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
