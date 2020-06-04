package com.example.spring_state_machine.config;

import com.example.spring_state_machine.enums.OrderStatus;
import com.example.spring_state_machine.enums.OrderStatusChangeEvent;
import com.example.spring_state_machine.listener.OrderPersistStateChangeListener;
import com.example.spring_state_machine.handler.PersistStateMachineHandler;
import com.example.spring_state_machine.service.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

/**
 * @author ：luoqi/02216
 * @date ：Created in 2020/6/4 5:26 下午
 * @description：Springboot注入Handler和Listener bean的Configuration类
 */
@Configuration
public class OrderPersistHandlerConfig {

    @Autowired
    private StateMachine<OrderStatus, OrderStatusChangeEvent> stateMachine;

    @Bean
    public OrderStateService persist() {
        PersistStateMachineHandler handler = persistStateMachineHandler();
        handler.addPersistStateChangeListener(persistStateChangeListener());
        return new OrderStateService(handler);
    }

    @Bean
    public PersistStateMachineHandler persistStateMachineHandler() {
        return new PersistStateMachineHandler(stateMachine);
    }

    @Bean
    public OrderPersistStateChangeListener persistStateChangeListener() {
        return new OrderPersistStateChangeListener();
    }

}
