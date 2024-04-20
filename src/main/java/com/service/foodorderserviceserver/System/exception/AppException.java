package com.service.foodorderserviceserver.System.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException{
    private int status;

    public AppException(int status, String message) {
        super(message);
        this.status = status;
    }
}
