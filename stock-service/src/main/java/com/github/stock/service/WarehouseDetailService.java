package com.github.stock.service;

import com.github.stock.entity.WarehouseDetail;

import java.util.List;

/**
 * (TWarehouseDetail)表服务接口
 *
 * @author makejava
 * @since 2020-12-16 14:15:56
 */
public interface WarehouseDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WarehouseDetail queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<WarehouseDetail> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param warehouseDetail 实例对象
     * @return 实例对象
     */
    WarehouseDetail insert(WarehouseDetail warehouseDetail);

    /**
     * 修改数据
     *
     * @param warehouseDetail 实例对象
     * @return 实例对象
     */
    WarehouseDetail update(WarehouseDetail warehouseDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}