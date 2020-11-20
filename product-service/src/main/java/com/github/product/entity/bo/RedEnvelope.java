package com.github.product.entity.bo;

import com.github.common.exception.Assert;
import com.github.common.exception.CommonException;
import com.github.product.enums.ProductServerResponseEnum;
import com.github.product.enums.RedEnvelopeEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author peach
 * @since 2020/11/19 16:11
 */
@Data
public abstract class RedEnvelope {

    /**
     * 红包类型
     */
    private Integer type;
    /**
     * 红包金额
     */
    private Integer amount;
    /**
     * 红包个数
     */
    private Integer size;
    /**
     * 红包描述
     */
    private String desc;

    /**
     * 红包拆分
     * @return java.util.List<java.lang.Integer> 拆分红包金额集合
     */
    public List<Integer> splitRedEnvelope() {
        validate(amount, size);
        return doSplitRedEnvelope();
    }

    /**
     * 红包拆分
     * @return java.util.List<java.lang.Integer> 拆分红包金额集合
     */
    public abstract List<Integer> doSplitRedEnvelope();

    /**
     * 工厂生成对应的不同类型的红包
     * @param type : 红包类型
     * @return com.github.product.entity.bo.RedEnvelope
     */
    public static RedEnvelope generate(Integer type, Integer amount, Integer size, String desc){
        if (type == RedEnvelopeEnum.NORMAL.getCode()) {
            NormalRedEnvelope normalRedEnvelope = new NormalRedEnvelope();
            normalRedEnvelope.setAmount(amount);
            normalRedEnvelope.setSize(size);
            normalRedEnvelope.setDesc(desc);
            normalRedEnvelope.setType(type);
            return normalRedEnvelope;
        }else if (type == RedEnvelopeEnum.LUCK.getCode()){
            LuckRedEnvelope luckRedEnvelope = new LuckRedEnvelope();
            luckRedEnvelope.setAmount(amount);
            luckRedEnvelope.setSize(size);
            luckRedEnvelope.setDesc(desc);
            luckRedEnvelope.setType(type);
            return luckRedEnvelope;
        }
        throw new CommonException("No red envelope type!");
    }


    /**
     * 校验最小值
     * @param amount : 金额
     * @param size : 个数
     */
    public void validate(Integer amount, Integer size) {
        // 1、校验参数
        Assert.notNull(amount, ProductServerResponseEnum.PARAM_ERROR);
        Assert.notNull(size, ProductServerResponseEnum.PARAM_ERROR);
        // 2、校验最小值
        Integer lower = (amount / size);
        Assert.less(lower, 1, ProductServerResponseEnum.PARAM_ERROR);
    }
}