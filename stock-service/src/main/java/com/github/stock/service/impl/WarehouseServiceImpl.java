package com.github.stock.service.impl;

import com.github.stock.entity.Warehouse;
import com.github.stock.mapper.WarehouseMapper;
import com.github.stock.service.WarehouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TWarehouse)表服务实现类
 *
 * @author makejava
 * @since 2020-12-16 14:15:56
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Resource
    private WarehouseMapper warehouseMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Warehouse queryById(Long id) {
        return this.warehouseMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Warehouse> queryAllByLimit(int offset, int limit) {
        return this.warehouseMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param warehouse 实例对象
     * @return 实例对象
     */
    @Override
    public Warehouse insert(Warehouse warehouse) {
        this.warehouseMapper.insert(warehouse);
        return warehouse;
    }

    /**
     * 修改数据
     *
     * @param warehouse 实例对象
     * @return 实例对象
     */
    @Override
    public Warehouse update(Warehouse warehouse) {
        this.warehouseMapper.update(warehouse);
        return this.queryById(warehouse.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.warehouseMapper.deleteById(id) > 0;
    }
}