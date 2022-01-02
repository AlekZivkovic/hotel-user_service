package com.raf.sk.hoteluserservice.exceptions;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends  CustomException {
    public AccessDeniedException(String message) {
        super(message, ErrorCode.ACCESS_DENIED, HttpStatus.FORBIDDEN);
    }
}
