package com.fileuploadanddownload.repositories;


import com.fileuploadanddownload.entities.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigInteger;
import java.util.List;

@RepositoryRestResource
public interface PhotoRepository extends MongoRepository<Photo, String> {
    public List<Photo> getAllByAnncId(String anncId);
}
