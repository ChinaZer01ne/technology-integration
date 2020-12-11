package com.github.order.mapper;

import com.github.order.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单表(TOrder)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-11 10:37:29
 */
@Repository
public interface OrderMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    Order queryById(Long orderId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Order> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param Order 实例对象
     * @return 对象列表
     */
    List<Order> queryAll(Order Order);

    /**
     * 新增数据
     *
     * @param Order 实例对象
     * @return 影响行数
     */
    int insert(Order Order);

    /**
     * 修改数据
     *
     * @param Order 实例对象
     * @return 影响行数
     */
    int update(Order Order);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(Long orderId);

}