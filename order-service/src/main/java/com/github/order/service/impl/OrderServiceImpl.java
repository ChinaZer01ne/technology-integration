package com.github.order.service.impl;

import com.github.common.core.exception.Assert;
import com.github.common.core.response.CommonServerResponseEnum;
import com.github.internal.api.order.dto.OrderDTO;
import com.github.internal.api.pay.message.PayResultMessage;
import com.github.internal.api.product.ProductClient;
import com.github.internal.api.product.ProductDTO;
import com.github.internal.api.stock.StockClient;
import com.github.order.command.impl.OrderCreateCommand;
import com.github.order.entity.Order;
import com.github.order.entity.OrderDetail;
import com.github.order.entity.vo.CartWareVO;
import com.github.order.enums.OrderStateEnum;
import com.github.order.mapper.OrderMapper;
import com.github.order.service.OrderService;
import com.github.order.state.Context;
import com.github.order.state.UnPaidState;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Zer01ne
 * @since 2020/11/29 1:24
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private StockClient stockClient;

    @Override
    public void create(OrderCreateCommand orderCreateCommand) {
        Assert.notNull(orderCreateCommand, CommonServerResponseEnum.FAIL);
        Order order = orderCreateCommand.convert();
        // 订单id TODO 后续使用雪花算法或者自定义发号器
        order.setOrderId(new Random().nextLong());
        // 订单流水号 TODO 后续使用自定义发号器
        order.setOrderCode(String.valueOf(new Random().nextLong()));
        order.setOrderState(OrderStateEnum.PRE.getCode());

        List<Long> productIds = orderCreateCommand.getCartWareList().stream().map(CartWareVO::getProductId).collect(Collectors.toList());
        List<ProductDTO> products = productClient.get(productIds);
        Map<Long, ProductDTO> productMap = products.stream().collect(Collectors.toMap(ProductDTO::getId, product -> product));
        for (OrderDetail orderDetail : order.getOrderDetailList()) {
            ProductDTO product = productMap.get(orderDetail.getProductId());
            orderDetail.setProductName(product.getProductName());
            orderDetail.setProductPrice(product.getProductPrice());
        }

        // TODO 分布式事务
        // 创建订单
        order.create();

        // 锁定库存（同步or异步）
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        stockClient.lock(orderDTO);

    }

    /**
     * 订单支付完成，修改订单状态
     * @param payResultMessage : 支付结果
     */
    @Override
    public void paid(PayResultMessage payResultMessage) {
        // TODO 分布式事务

        Order order = orderMapper.queryById(payResultMessage.getOrderId());
        // TODO 监听支付完成消息，修改订单状态
        UnPaidState unPaidState = new UnPaidState();
        Context context = new Context(unPaidState);
        context.doAction();
        order.setOrderState(OrderStateEnum.UN_SEND.getCode());
        orderMapper.update(order);

    }

    @Override
    public Order getById(Long orderId) {
        return orderMapper.queryById(orderId);
    }


}
