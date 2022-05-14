package com.demo.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum EntityStatus implements IEnum<Integer> {
    /**
     * 删除/可用
     */
    STATUS_INACTIVE(0, "删除"),
    STATUS_ACTIVE(1, "可用");

    private final int    value;
    private final String name;

    EntityStatus(int value, String name) {
        this.value = value;
        this.name  = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
