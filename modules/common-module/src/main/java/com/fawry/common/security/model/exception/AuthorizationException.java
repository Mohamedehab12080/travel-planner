package com.fawry.common.security.model.exception;


import com.fawry.common.model.exception.AppException;
import com.fawry.common.model.interfaces.Errors;
import lombok.Getter;

@Getter
public class AuthorizationException extends AppException {

    public AuthorizationException(Errors error) {
        super(error);
    }

    public AuthorizationException(Errors error, Object... args) {
        super(error, args);
    }
}
