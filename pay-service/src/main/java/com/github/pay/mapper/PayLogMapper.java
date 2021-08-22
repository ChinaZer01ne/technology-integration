package com.github.pay.mapper;

import com.github.pay.entity.PayLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 支付日志表(PayLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-16 15:57:59
 */
public interface PayLogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PayLog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PayLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param payLog 实例对象
     * @return 对象列表
     */
    List<PayLog> queryAll(PayLog payLog);

    /**
     * 新增数据
     *
     * @param payLog 实例对象
     * @return 影响行数
     */
    int insert(PayLog payLog);

    /**
     * 修改数据
     *
     * @param payLog 实例对象
     * @return 影响行数
     */
    int update(PayLog payLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据payId查询
     * @param payId :
     * @return com.github.pay.entity.PayLog
     */
    PayLog getByPayId(Long payId);
}