package com.example.payment_service.Repositories;

import com.example.payment_service.dtos.ForSellInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SellInfoRepository extends MongoRepository<ForSellInfo,String> {
    ForSellInfo findForSellInfoByImmoid(String immoId);
}
