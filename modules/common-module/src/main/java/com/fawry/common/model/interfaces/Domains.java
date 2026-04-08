package com.fawry.common.model.interfaces;

public interface Domains {
    Integer id();

    String name();

    default String label() {
        return this.name();
    }


}

