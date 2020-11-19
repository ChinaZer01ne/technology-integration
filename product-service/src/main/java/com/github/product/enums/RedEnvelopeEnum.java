package com.github.product.enums;

/**
 * 红包类型枚举
 * @author peach
 * @since 2020/11/19 16:53
 */
public enum  RedEnvelopeEnum {
    /**
     * 红包类型
     */
    NORMAL(1, "普通红包"),
    LUCK(2, "拼手气红包");
    /**
     * 编码
     */
    private int code;
    /**
     * 描述
     */
    private String desc;

    RedEnvelopeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}