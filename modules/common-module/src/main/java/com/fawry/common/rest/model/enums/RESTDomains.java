package com.fawry.common.rest.model.enums;


import com.fawry.common.model.interfaces.Domains;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RESTDomains implements Domains {
    REST(101);

    private final Integer id;

    @Override
    public Integer id() {
        return id;
    }

}
