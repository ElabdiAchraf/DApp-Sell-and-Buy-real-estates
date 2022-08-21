package com.example.order.Repository;


import com.example.order.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    public List<Order> findOrdersByIdImmobilier(String idImmobilier);
    public List<Order> findOrdersByIdUser(Long idUser);
    public List<Order> deleteAllByIdImmobilier(String idImmobilier);
}
