package com.example.spring_state_machine.service;

import com.example.spring_state_machine.models.Order;

import java.util.Map;

public interface OrderService {

    Order creat();

    Order pay(int id);

    Order deliver(int id);

    Order receive(int id);

    Map<Integer, Order> getOrders();
}
