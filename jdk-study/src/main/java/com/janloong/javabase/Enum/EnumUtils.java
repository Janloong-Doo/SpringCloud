package com.janloong.javabase.Enum;

/**
 * des: ，枚举类测试
 * @author Janloong
 * @create 2017-07-12 下午6:05
 **/
public enum EnumUtils {
    A("a"),
    B("b"),
    C("c");

    private String value;

    private EnumUtils(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
