package com.fawry.destination.model.enums;

import com.fawry.common.model.interfaces.Domains;
import com.fawry.common.model.interfaces.Errors;
import lombok.AllArgsConstructor;

import static com.fawry.destination.model.enums.DestinationDomains.DESTINATION;

@AllArgsConstructor
public enum DestinationErrors implements Errors {
    DESTINATION_NOT_FOUND(DESTINATION,"0001","Destination {0} not found "),
    DESTINATION_ALREADY_EXIST(DESTINATION,"0002","Destination {0} already exists "),
    INVALID_STATE(DESTINATION,"0003","Destination is not in {0} state"),
    DESTINATION_ALREADY_REJECTED(DESTINATION,"0004","Destination {0} already rejected"),
    DESTINATION_ALREADY_APPROVED(DESTINATION,"0005","Destination {0} already approved"),
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
