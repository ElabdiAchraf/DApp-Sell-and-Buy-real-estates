package com.profile.repositories;

import com.profile.entities.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigInteger;


@RepositoryRestResource
public interface ProfileRepository extends MongoRepository<Profile, BigInteger> {
    public Profile findProfileByUserId(Long userId);
    public Profile findProfileById(BigInteger Id);
}
