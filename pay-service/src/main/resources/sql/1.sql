CREATE TABLE `t_pay_log`
(
    `id`               bigint(19)    NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `user_id`         bigint(19)    NOT NULL COMMENT '用户id',
    `biz_no`           bigint(19)             DEFAULT NULL COMMENT '业务编号',
    `pay_type`         varchar(60)            DEFAULT NULL COMMENT '支付方式',
    `opt_type`         tinyint(4)    NOT NULL COMMENT '请求操作类型(1.支付,2.退款,3.查询支付,4.查询退款,5.撤销交易)',
    `amount`           double(16, 6) NOT NULL DEFAULT '0.000000' COMMENT '金额',
    `response_status`  tinyint(4)    NOT NULL COMMENT '响应结果状态（枚举：1,失败;2,成功;3,支付中,4.代码异常）',
    `response_result`  varchar(2000)          DEFAULT NULL COMMENT '响应内容',
    `request_time`      datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '请求时间',
    `response_time`    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '响应时间',
    `biz_type`         varchar(30)            DEFAULT NULL COMMENT '业务类型',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='支付日志表';