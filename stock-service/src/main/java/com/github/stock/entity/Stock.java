package com.github.stock.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TStock)实体类
 *
 * @author makejava
 * @since 2020-12-16 14:15:53
 */
public class Stock implements Serializable {
    private static final long serialVersionUID = -50739899798125157L;
    /**
    * 仓库id
    */
    private Long id;
    /**
    * 库房编号
    */
    private Long storeId;
    /**
    * 商品id
    */
    private Long productId;
    /**
    * 现货数量
    */
    private Long actualNum;
    /**
    * 锁定数量
    */
    private Long lockNum;
    /**
    * 订单预占数量
    */
    private Long orderLockNum;
    /**
    * 预售数量
    */
    private Long preSellNum;
    /**
    * 预售状态
    */
    private Long preSellState;
    /**
    * 库存状态  0:关闭 1:开启
    */
    private Object stockState;
    /**
    * 数据状态  0:删除 1:有效
    */
    private Object status;
    /**
    * 创建人
    */
    private Long createUser;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 最终修改时间
    */
    private Date modifyTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getActualNum() {
        return actualNum;
    }

    public void setActualNum(Long actualNum) {
        this.actualNum = actualNum;
    }

    public Long getLockNum() {
        return lockNum;
    }

    public void setLockNum(Long lockNum) {
        this.lockNum = lockNum;
    }

    public Long getOrderLockNum() {
        return orderLockNum;
    }

    public void setOrderLockNum(Long orderLockNum) {
        this.orderLockNum = orderLockNum;
    }

    public Long getPreSellNum() {
        return preSellNum;
    }

    public void setPreSellNum(Long preSellNum) {
        this.preSellNum = preSellNum;
    }

    public Long getPreSellState() {
        return preSellState;
    }

    public void setPreSellState(Long preSellState) {
        this.preSellState = preSellState;
    }

    public Object getStockState() {
        return stockState;
    }

    public void setStockState(Object stockState) {
        this.stockState = stockState;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}