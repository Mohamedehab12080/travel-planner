package com.fawry.user.model.enums;

import com.fawry.common.model.interfaces.Domains;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserDomains implements Domains {

    USER(2201),;
    private final Integer id;

    @Override
    public Integer id() {
        return id;
    }
}
