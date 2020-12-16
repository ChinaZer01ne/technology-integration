package com.github.stock.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TWarehouseDetail)实体类
 *
 * @author makejava
 * @since 2020-12-16 14:15:56
 */
public class WarehouseDetail implements Serializable {
    private static final long serialVersionUID = 313986238671482279L;
    /**
    * 仓库id
    */
    private Long id;
    /**
    * 库房编号
    */
    private Long storeId;
    /**
    * 库房名称
    */
    private Long storeName;
    /**
    * 库房类型 1、线下仓库，2、虚拟仓库，3、配送中心，4、线下自提点
    */
    private Object storeType;
    /**
    * 国家
    */
    private Long countryId;
    /**
    * 省
    */
    private Long provinceId;
    /**
    * 市
    */
    private Long cityId;
    /**
    * 地区
    */
    private Long areaId;
    /**
    * 地址
    */
    private String address;
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

    public Long getStoreName() {
        return storeName;
    }

    public void setStoreName(Long storeName) {
        this.storeName = storeName;
    }

    public Object getStoreType() {
        return storeType;
    }

    public void setStoreType(Object storeType) {
        this.storeType = storeType;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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