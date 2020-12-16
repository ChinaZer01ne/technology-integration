package com.github.stock.service.impl;

import com.github.internal.api.order.dto.OrderDTO;
import com.github.internal.api.order.dto.OrderDetailDTO;
import com.github.stock.entity.Stock;
import com.github.stock.mapper.StockMapper;
import com.github.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author peach
 * @since 2020/12/11 16:41
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public boolean lock(OrderDTO order) {
        return stockMapper.lock(order.getOrderDetailList()) > 1;
    }

    @Override
    public boolean deduct(OrderDTO order) {
        return stockMapper.deduct(order.getOrderDetailList()) > 1;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Stock queryById(Long id) {
        return this.stockMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Stock> queryAllByLimit(int offset, int limit) {
        return this.stockMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param stock 实例对象
     * @return 实例对象
     */
    @Override
    public Stock insert(Stock stock) {
        this.stockMapper.insert(stock);
        return stock;
    }

    /**
     * 修改数据
     *
     * @param stock 实例对象
     * @return 实例对象
     */
    @Override
    public Stock update(Stock stock) {
        this.stockMapper.update(stock);
        return this.queryById(stock.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.stockMapper.deleteById(id) > 0;
    }
}