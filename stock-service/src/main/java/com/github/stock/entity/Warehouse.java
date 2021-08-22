package com.github.stock.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TWarehouse)实体类
 *
 * @author makejava
 * @since 2020-12-16 14:15:56
 */
public class Warehouse implements Serializable {
    private static final long serialVersionUID = -97976473836344899L;
    /**
    * 仓库id
    */
    private Long id;
    /**
    * 库房编号
    */
    private Long storeId;
    /**
    * 库房类型 1、线下仓库，2、虚拟仓库，3、配送中心，4、线下自提点
    */
    private Object storeType;
    /**
    * 仓库状态  0:关闭 1:开启
    */
    private Object state;
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

    public Object getStoreType() {
        return storeType;
    }

    public void setStoreType(Object storeType) {
        this.storeType = storeType;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
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