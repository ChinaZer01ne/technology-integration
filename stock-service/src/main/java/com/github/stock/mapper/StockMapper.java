package com.github.stock.mapper;

import com.github.internal.api.order.dto.OrderDTO;
import com.github.internal.api.order.dto.OrderDetailDTO;
import com.github.stock.entity.Stock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author peach 
 * @since 2020/12/16 14:30
 */
@Repository
public interface StockMapper {

    /**
     * 冻结库存
     * @param orderDetailList :
     * @return boolean
     */
    int lock(List<OrderDetailDTO> orderDetailList);

    /**
     * 扣减库存
     * @param orderDetailList :
     * @return boolean
     */
    int deduct(List<OrderDetailDTO> orderDetailList);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Stock queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Stock> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param stock 实例对象
     * @return 对象列表
     */
    List<Stock> queryAll(Stock stock);

    /**
     * 新增数据
     *
     * @param stock 实例对象
     * @return 影响行数
     */
    int insert(Stock stock);

    /**
     * 修改数据
     *
     * @param stock 实例对象
     * @return 影响行数
     */
    int update(Stock stock);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
}