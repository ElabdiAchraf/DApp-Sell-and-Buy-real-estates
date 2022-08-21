package com.example.payment_service.Repositories;

import com.example.payment_service.dtos.StatusPayment;
import com.example.payment_service.entities.Payment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PaymentRepository extends MongoRepository<Payment,String> {
    Payment findPaymentById(String id);
    Payment findPaymentByImmoid(String id);
    List<Payment> findAllByStatusPayment(StatusPayment statusImmobilier);
}
