package com.github.pay.service.impl;

import com.github.pay.entity.PayLog;
import com.github.pay.mapper.PayLogMapper;
import com.github.pay.service.PayLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 支付日志表(PayLog)表服务实现类
 *
 * @author makejava
 * @since 2020-12-16 15:58:00
 */
@Service
public class PayLogServiceImpl implements PayLogService {
    @Resource
    private PayLogMapper payLogMapper;


    @Override
    public PayLog getByPayId(Long payId) {
        return payLogMapper.getByPayId(payId);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PayLog queryById(Long id) {
        return this.payLogMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<PayLog> queryAllByLimit(int offset, int limit) {
        return this.payLogMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param PayLog 实例对象
     * @return 实例对象
     */
    @Override
    public PayLog insert(PayLog PayLog) {
        this.payLogMapper.insert(PayLog);
        return PayLog;
    }

    /**
     * 修改数据
     *
     * @param PayLog 实例对象
     * @return 实例对象
     */
    @Override
    public PayLog update(PayLog PayLog) {
        this.payLogMapper.update(PayLog);
        return this.queryById(PayLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.payLogMapper.deleteById(id) > 0;
    }
}