package com.fawry.common.security.model.exception;


import com.fawry.common.model.exception.AppException;
import com.fawry.common.model.interfaces.Errors;
import lombok.Getter;

@Getter
public class AuthenticationException extends AppException {

    public AuthenticationException(Errors error) {
        super(error);
    }

    public AuthenticationException(Errors error, Object... args) {
        super(error, args);
    }
}
