package com.github.order.entity;

import com.github.common.core.annotation.ddd.ValueObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单明细表(OrderDetail)实体类
 *
 * @author makejava
 * @since 2020-12-11 10:37:12
 */
@Data
@ValueObject
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = -69204420471299825L;
    /**
    * id
    */
    private Long id;
    /**
    * 关联订单id
    */
    private Long orderId;
    /**
    * 商品id
    */
    private Long productId;
    /**
    * 商品名
    */
    private String productName;
    /**
    * 商品数量
    */
    private Integer productNum;
    /**
    * 商品价格
    */
    private Integer productPrice;


}