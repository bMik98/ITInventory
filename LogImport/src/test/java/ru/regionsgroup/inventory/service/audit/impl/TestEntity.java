package ru.regionsgroup.inventory.service.audit.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Mbedritskiy
 */
public class TestEntity {
    @JsonProperty
    private String name;

    @JsonProperty
    private boolean sex;

    public TestEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}