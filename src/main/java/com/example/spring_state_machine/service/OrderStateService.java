package com.example.spring_state_machine.service;

import com.example.spring_state_machine.enums.OrderStatus;
import com.example.spring_state_machine.enums.OrderStatusChangeEvent;
import com.example.spring_state_machine.handler.PersistStateMachineHandler;
import com.example.spring_state_machine.models.Order;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


/**
 * @author ：luoqi/02216
 * @date ：Created in 2020/6/4 5:38 下午
 * @description：订单服务类Service
 */
@Component
public class OrderStateService {

    private PersistStateMachineHandler handler;

    public OrderStateService(PersistStateMachineHandler handler) {
        this.handler = handler;
    }

    public String listDbEntires() {
        // mock 获取数据表
        Order order = new Order();
        order.setId(1);
        order.setOrderId(10);
        order.setStatus(OrderStatus.WAIT_PAYMENT);
        return order.toString();
    }

    public boolean change(int orderNum, OrderStatusChangeEvent event) {
        // mock 获取数据表
        Order order = new Order();
        order.setId(1);
        order.setOrderId(10);
        order.setStatus(OrderStatus.WAIT_PAYMENT);
        return handler.handleEventWithState(MessageBuilder.withPayload(event).setHeader("order", orderNum).build(), order.getStatus());
    }
}
