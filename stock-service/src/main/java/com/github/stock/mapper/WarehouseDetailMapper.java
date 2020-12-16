package com.github.stock.mapper;

import com.github.stock.entity.WarehouseDetail;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TWarehouseDetail)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-16 14:15:56
 */
public interface WarehouseDetailMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WarehouseDetail queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<WarehouseDetail> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param warehouseDetail 实例对象
     * @return 对象列表
     */
    List<WarehouseDetail> queryAll(WarehouseDetail warehouseDetail);

    /**
     * 新增数据
     *
     * @param warehouseDetail 实例对象
     * @return 影响行数
     */
    int insert(WarehouseDetail warehouseDetail);

    /**
     * 修改数据
     *
     * @param warehouseDetail 实例对象
     * @return 影响行数
     */
    int update(WarehouseDetail warehouseDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}