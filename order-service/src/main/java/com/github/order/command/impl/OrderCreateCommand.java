package com.github.order.command.impl;

import com.github.order.command.Command;
import com.github.order.entity.Order;
import com.github.order.entity.OrderDetail;
import com.github.order.entity.vo.CartWareVO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peach
 * @since 2020/12/11 10:16
 */
@Data
public class OrderCreateCommand implements Command {

    /**
     * 订单金额
     */
    private Integer amount;

    private List<CartWareVO> cartWareList;

    /**
     * 转换订单对象
     * @return com.github.order.entity.Order
     */
    public Order convert() {
        Order order = new Order();
        order.setAmount(amount);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (CartWareVO cartWare : cartWareList) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProductId(cartWare.getProductId());
            orderDetail.setProductNum(cartWare.getProductNum());
            orderDetailList.add(orderDetail);
        }
        order.setOrderDetailList(orderDetailList);
        return order;
    }
}