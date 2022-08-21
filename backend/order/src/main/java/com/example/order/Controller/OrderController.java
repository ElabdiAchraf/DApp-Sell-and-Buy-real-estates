package com.example.order.Controller;


import com.example.order.Dtos.OrderInput;
import com.example.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.order.entities.Order;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/OrderImmobilier")
    public Order OrderImmobilier(@RequestBody OrderInput orderInput){
        return orderService.OrderImmobilier(orderInput);
    }
    @DeleteMapping("/DeleteOrder/{idOrder}")
    public void DeleteOrder(@PathVariable String idOrder){
        orderService.deleteOrder(idOrder);
    }
    @GetMapping("/UserOrders/{idUser}")
    public List<Order> UserOrders(@PathVariable Long idUser){
        return orderService.getUserOrders(idUser);
    }

    @DeleteMapping("/DeleteImmobilierOrders/{idImmobilier}")
    public void DeleteImmobilierOrders(@PathVariable String idImmobilier){
         orderService.deleteImmobilierOrders(idImmobilier);
    }
    @GetMapping("/GetImmobilierOrders/{idImmobilier}")
    public List<Order> GetImmobilierOrders(@PathVariable String idImmobilier){
        return orderService.getImmobilierOrders(idImmobilier);
    }
}
