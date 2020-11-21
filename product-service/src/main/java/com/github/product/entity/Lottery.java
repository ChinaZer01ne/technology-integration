package com.github.product.entity;

import lombok.Data;

/**
 * 抽奖实体
 * @author Zer01ne
 * @since 2020/11/21 11:07
 */
@Data
public class Lottery {

    private Long id;

    private Long serialNumber;

    private String name;

    private Integer probability;

}
