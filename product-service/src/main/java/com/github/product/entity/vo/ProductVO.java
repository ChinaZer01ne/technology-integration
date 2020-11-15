package com.github.product.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Zer01ne
 * @since 2020/11/15 19:06
 */
@ApiModel("商品展示对象")
@Data
public class ProductVO {

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
     * 浏览量
     * */
    @ApiModelProperty("浏览量")
    private Long views;
}
