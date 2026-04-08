package com.fawry.common.security.model.enums;

import com.fawry.common.model.interfaces.Domains;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SecurityDomains implements Domains {

    JWT_TOKEN(1001);
    private final Integer id;

    @Override
    public Integer id() {
        return id;
    }
}
