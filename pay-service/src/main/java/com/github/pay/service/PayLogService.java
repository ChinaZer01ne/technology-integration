package com.github.pay.service;

import com.github.pay.entity.PayLog;

import java.util.List;

/**
 * @author Zer01ne
 * @since 2020/11/28 23:55
 */
public interface PayLogService {


    /**
     * 查询支付记录
     * @param payId : 支付id
     * @return com.github.pay.entity.PayLog
     */
    PayLog getByPayId(Long payId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PayLog queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PayLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param PayLog 实例对象
     * @return 实例对象
     */
    PayLog insert(PayLog PayLog);

    /**
     * 修改数据
     *
     * @param PayLog 实例对象
     * @return 实例对象
     */
    PayLog update(PayLog PayLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
