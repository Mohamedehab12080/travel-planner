package com.fawry.destination.model.enums;

import com.fawry.common.model.interfaces.Domains;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DestinationDomains implements Domains {

    DESTINATION(2101),;
    private final Integer id;
    @Override
    public Integer id() {
        return id;
    }

}
