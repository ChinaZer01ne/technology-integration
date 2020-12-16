package com.github.stock.service;

import com.github.internal.api.order.dto.OrderDTO;
import com.github.stock.entity.Stock;

import java.util.List;

/**
 * @author peach
 * @since 2020/12/11 16:40
 */
public interface StockService {
    /**
     * 锁定库存
     * @param order : 订单
     * @return boolean
     */
    boolean lock(OrderDTO order);

    /**
     * 扣减库存
     * @param order : 订单
     * @return boolean
     */
    boolean deduct(OrderDTO order);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Stock queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Stock> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param stock 实例对象
     * @return 实例对象
     */
    Stock insert(Stock stock);

    /**
     * 修改数据
     *
     * @param stock 实例对象
     * @return 实例对象
     */
    Stock update(Stock stock);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}