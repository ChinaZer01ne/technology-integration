package com.github.stock.service.impl;

import com.github.stock.entity.WarehouseDetail;
import com.github.stock.mapper.WarehouseDetailMapper;
import com.github.stock.service.WarehouseDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TWarehouseDetail)表服务实现类
 *
 * @author makejava
 * @since 2020-12-16 14:15:56
 */
@Service
public class WarehouseDetailServiceImpl implements WarehouseDetailService {
    @Resource
    private WarehouseDetailMapper warehouseDetailMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WarehouseDetail queryById(Long id) {
        return this.warehouseDetailMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<WarehouseDetail> queryAllByLimit(int offset, int limit) {
        return this.warehouseDetailMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param warehouseDetail 实例对象
     * @return 实例对象
     */
    @Override
    public WarehouseDetail insert(WarehouseDetail warehouseDetail) {
        this.warehouseDetailMapper.insert(warehouseDetail);
        return warehouseDetail;
    }

    /**
     * 修改数据
     *
     * @param warehouseDetail 实例对象
     * @return 实例对象
     */
    @Override
    public WarehouseDetail update(WarehouseDetail warehouseDetail) {
        this.warehouseDetailMapper.update(warehouseDetail);
        return this.queryById(warehouseDetail.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.warehouseDetailMapper.deleteById(id) > 0;
    }
}