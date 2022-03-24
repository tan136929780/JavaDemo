package test.demo.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum Gender implements IEnum<Integer> {
    /**
     * 男/女
     */
    GENDER_MALE(1, "男"),
    GENDER_FAMALE(2, "女");

    private final int    value;
    private final String name;

    Gender(int value, String name) {
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
