package com.example.spring_state_machine.listener;

import com.example.spring_state_machine.enums.OrderStatus;
import com.example.spring_state_machine.enums.OrderStatusChangeEvent;
import com.example.spring_state_machine.handler.PersistStateMachineHandler;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

/**
 * @author ：luoqi/02216
 * @date ：Created in 2020/6/4 5:10 下午
 * @description：持久化状态发生变化的订单实体的Listener实现类
 */
public class OrderPersistStateChangeListener implements PersistStateMachineHandler.PersistStateChangeListener {

    @Override
    public void onPersist(State<OrderStatus, OrderStatusChangeEvent> state, Message<OrderStatusChangeEvent> message,
                          Transition<OrderStatus, OrderStatusChangeEvent> transition, StateMachine<OrderStatus, OrderStatusChangeEvent> stateMachine) {
        if (message != null && message.getHeaders().containsKey("order")) {
            Integer orderNum = message.getHeaders().get("order", Integer.class);
            // save持久化
            OrderStatus status = state.getId();
            System.out.println("save order: " + orderNum + ", order state: " + status);
        }
    }

}
