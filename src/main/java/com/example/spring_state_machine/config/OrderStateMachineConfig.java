package com.example.spring_state_machine.config;

/**
 * @author ：luoqi/02216
 * @date ：Created in 2020/6/4 8:29 下午
 * @description：订单状态机
 */

import com.example.spring_state_machine.enums.OrderStatus;
import com.example.spring_state_machine.enums.OrderStatusChangeEvent;
import com.example.spring_state_machine.models.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

/**
 * 订单状态机配置
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvent> {

    /**
     * 配置状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderStatus.WAIT_PAYMENT)
                .states(EnumSet.allOf(OrderStatus.class));
    }

    /**
     * 配置状态转换事件关系
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvent> transitions) throws Exception {
        transitions
                .withExternal().source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER).event(OrderStatusChangeEvent.PAYED)
                .and()
                .withExternal().source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE).event(OrderStatusChangeEvent.DELIVERY)
                .and()
                .withExternal().source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH).event(OrderStatusChangeEvent.RECEIVED);
    }

    /**
     * 持久化配置
     * 实际使用中，可以配合redis等，进行持久化操作
     * @return
     */
    @Bean
    public StateMachinePersister<OrderStatus, OrderStatusChangeEvent, Order> persister(){
        return new DefaultStateMachinePersister<>(new StateMachinePersist<OrderStatus, OrderStatusChangeEvent, Order>() {
            @Override
            public void write(StateMachineContext<OrderStatus, OrderStatusChangeEvent> context, Order order) throws Exception {
                //此处并没有进行持久化操作
            }

            @Override
            public StateMachineContext<OrderStatus, OrderStatusChangeEvent> read(Order order) throws Exception {
                //此处直接获取order中的状态，其实并没有进行持久化读取操作
                return new DefaultStateMachineContext<>(order.getStatus(), null, null, null);
            }
        });
    }
}