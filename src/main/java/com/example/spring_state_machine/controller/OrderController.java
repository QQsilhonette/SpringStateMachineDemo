package com.example.spring_state_machine.controller;

import com.example.spring_state_machine.enums.OrderStatusChangeEvent;
import com.example.spring_state_machine.service.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：luoqi/02216
 * @date ：Created in 2020/6/4 5:33 下午
 * @description：订单controller
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderStateService orderStateService;

    /**
     * 列出所有的订单列表
     *
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity orders() {
        String orders = orderStateService.listDbEntires();
        return new ResponseEntity(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId}", method = {RequestMethod.POST})
    public ResponseEntity processOrderState(@PathVariable("orderId") Integer orderId, @RequestParam("event") OrderStatusChangeEvent event) {
        Boolean result = orderStateService.change(orderId, event);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
