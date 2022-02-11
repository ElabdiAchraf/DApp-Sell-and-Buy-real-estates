package com.example.order.services;



import com.example.order.Dtos.OrderInput;
import com.example.order.entities.Order;

import java.util.List;

public interface OrderService {
    public Order OrderImmobilier(OrderInput orderInput);

    public void deleteImmobilierOrders(String idImmobilier);

    public List<Order> getImmobilierOrders(String idImmobilier);
    public List<Order> getUserOrders(Long idUser);
    public void deleteOrder(String idOrder);
}
