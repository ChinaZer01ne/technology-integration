CREATE TABLE `t_order_info`
(
    `order_id`    bigint(20) NOT NULL COMMENT '订单id',
    `order_code`  varchar(30) NOT NULL COMMENT '订单流水号',
    `order_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '订单状态  1:待付款 2:已完成 3:退款中 4:已关闭',
    `amount`      int(11) NOT NULL DEFAULT '0.000000' COMMENT '订单金额',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最终修改时间',
    PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='订单表';

CREATE TABLE `t_order_detail`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `order_id`   bigint(20) DEFAULT NULL COMMENT '订单id',
    `ware_code`  bigint(20) NOT NULL DEFAULT '0' COMMENT '商品编码',
    `ware_name`  varchar(200) NOT NULL COMMENT '商品名',
    `ware_num`   int(11) NOT NULL DEFAULT '0.0000' COMMENT '商品数量',
    `ware_price` int(11) NOT NULL DEFAULT '0.000000' COMMENT '价格',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='订单明细表';
