package com.fawry.common.rest.model.enums;


import com.fawry.common.model.interfaces.Domains;
import com.fawry.common.model.interfaces.Errors;
import lombok.AllArgsConstructor;

import static com.fawry.common.rest.model.enums.RESTDomains.REST;

@AllArgsConstructor
public enum RESTErrors implements Errors {
    INTERNAL_SERVER_ERROR(REST, "0001", "Internal Server Error"),
    UN_AUTHENTICATED_REQ(REST, "0002", "Request isn't Authenticated"),
    UN_AUTHORIZED_REQ(REST, "0003", "User isn't Authorized to access this resource"),
    INVALID_REQUEST(REST, "0004", "Invalid Request received"),
    ;
    private final Domains domain;
    private final String code;
    private final String messageEn;

    @Override
    public Domains domain() {
        return domain;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String messageEn() {
        return messageEn;
    }

}
