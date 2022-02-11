package com.example.order.services;

import com.example.order.Dtos.OrderInput;
import com.example.order.Dtos.UserExistDto;
import com.example.order.Dtos.UserResp;
import com.example.order.Repository.OrderRepository;
import com.example.order.entities.Order;
import com.example.order.restClient.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRestClient userRestClient;

    @Override
    public Order OrderImmobilier(OrderInput orderInput) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserExistDto userExistDto = new UserExistDto();
        userExistDto.setUsername((String) auth.getPrincipal());
        UserResp userResp = userRestClient.getUser(userExistDto);
        Order order = new Order(null,orderInput.getIdImmobilier(),orderInput.getBuyerAddress(),userResp.getId(),orderInput.getPrice());
        return orderRepository.save(order);
    }

    @Override
    public void deleteImmobilierOrders(String idImmobilier) {
        orderRepository.deleteAllByIdImmobilier(idImmobilier);
        System.out.println("Orders deleted");
    }

    @Override
    public List<Order> getImmobilierOrders(String idImmobilier) {

        return orderRepository.findOrdersByIdImmobilier(idImmobilier);
    }

    @Override
    public List<Order> getUserOrders(Long idUser) {

        return orderRepository.findOrdersByIdUser(idUser);
    }

    @Override
    public void deleteOrder(String idOrder) {
        orderRepository.deleteById(idOrder);
        System.out.println("Order deleted");
    }
}
