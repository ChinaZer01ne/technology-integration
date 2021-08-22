create table `t_warehouse`
(
    `id`          bigint(20) NOT NULL COMMENT '仓库id',
    `store_id`    bigint(20) NOT NULL COMMENT '库房编号',
    `store_type`  tinyint(2) NOT NULL COMMENT '库房类型 1、线下仓库，2、虚拟仓库，3、配送中心，4、线下自提点',
    `state`       tinyint(2) NOT NULL DEFAULT '0' COMMENT '仓库状态  0:关闭 1:开启',
    `create_user` bigint(20) NOT NULL COMMENT '创建人',
    `create_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最终修改时间',
    PRIMARY KEY (`id`) USING BTREE
);

create table `t_warehouse_detail`
(
    `id`          bigint(20) NOT NULL COMMENT '仓库id',
    `store_id`    bigint(20) NOT NULL COMMENT '库房编号',
    `store_name`    bigint(20) NOT NULL COMMENT '库房名称',
    `store_type`  tinyint(2) NOT NULL COMMENT '库房类型 1、线下仓库，2、虚拟仓库，3、配送中心，4、线下自提点',
    `country_id`    bigint(20) NOT NULL COMMENT '国家',
    `province_id`    bigint(20) NOT NULL COMMENT '省',
    `city_id`    bigint(20) NOT NULL COMMENT '市',
    `area_id`    bigint(20) NOT NULL COMMENT '地区',
    `address`    varchar(100) NOT NULL COMMENT '地址',
    `state`       tinyint(2) NOT NULL DEFAULT '0' COMMENT '仓库状态  0:关闭 1:开启',
    `create_user` bigint(20) NOT NULL COMMENT '创建人',
    `create_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最终修改时间',
    PRIMARY KEY (`id`) USING BTREE
);

create table `t_stock`
(
    `id`          bigint(20) NOT NULL COMMENT '仓库id',
    `store_id`    bigint(20) NOT NULL COMMENT '库房编号',
    `product_id`    bigint(20) NOT NULL COMMENT '商品id',
    `actual_num`    bigint(20) NOT NULL COMMENT '现货数量',
    `lock_num`    bigint(20) NOT NULL COMMENT '锁定数量',
    `order_lock_num`    bigint(20) NOT NULL COMMENT '订单预占数量',
    `pre_sell_num`    bigint(20) NOT NULL COMMENT '预售数量',
    `pre_sell_state`    bigint(20) NOT NULL COMMENT '预售状态',
    `stock_state`       tinyint(2) NOT NULL DEFAULT '0' COMMENT '库存状态  0:关闭 1:开启',
    `status`       tinyint(2) NOT NULL DEFAULT '0' COMMENT '数据状态  0:删除 1:有效',
    `create_user` bigint(20) NOT NULL COMMENT '创建人',
    `create_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最终修改时间',
    PRIMARY KEY (`id`) USING BTREE
);