package com.fawry.common.model.exception;

import com.fawry.common.model.interfaces.Errors;
import lombok.Getter;

@Getter
public class InternalServerException extends AppException {

    public InternalServerException(Errors error) {
        super(error);
    }

    public InternalServerException(Errors error, Object... args) {
        super(error, args);
    }
}
