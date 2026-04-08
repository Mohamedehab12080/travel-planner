package com.fawry.common.model.enums;

import com.fawry.common.model.interfaces.Domains;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DBDomains implements Domains {

    QUERY_BUILDER(1201);

    private final Integer id;

    @Override
    public Integer id() {
        return id;
    }
}
