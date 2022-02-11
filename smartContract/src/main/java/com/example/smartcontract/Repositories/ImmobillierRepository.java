package com.example.smartcontract.Repositories;

import com.example.smartcontract.dtos.StatusImmobilier;
import com.example.smartcontract.entities.Immobillier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ImmobillierRepository extends MongoRepository<Immobillier,String> {
    Immobillier findImmobillierById(String id);
    List<Immobillier> findAllByStatus(StatusImmobilier statusImmobilier);
    List<Immobillier> findAllByForSell(boolean bol);
    List<Immobillier> findAllByOwnerAddress(String address);
}
