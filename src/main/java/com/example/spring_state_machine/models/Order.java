package com.example.spring_state_machine.models;

import com.example.spring_state_machine.enums.OrderStatus;
import lombok.Data;
import lombok.ToString;

/**
 * @author ：luoqi/02216
 * @date ：Created in 2020/6/4 10:43 上午
 * @description：订单Entity
 */

@ToString
@Data
public class Order {

    private Integer id;

    private Integer orderId;

    private OrderStatus status;

}
