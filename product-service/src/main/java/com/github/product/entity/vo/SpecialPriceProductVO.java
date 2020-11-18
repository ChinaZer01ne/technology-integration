package com.github.product.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 特价商品返回实体
 * @author peach
 * @since 2020/11/18 16:12
 */
@ApiModel("商品展示对象")
@Data
public class SpecialPriceProductVO {
    /**
     * id
     * */
    @ApiModelProperty("id")
    private Long id;
    /**
     * 名称
     * */
    @ApiModelProperty("名称")
    private String name;
    /**
     * 价格
     * */
    @ApiModelProperty("价格")
    private Integer price;
    /**
     * 浏览量
     * */
    @ApiModelProperty("浏览量")
    private Long views;
}