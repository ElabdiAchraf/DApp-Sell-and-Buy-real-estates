package com.example.jwt.repositories;

import com.example.jwt.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    public AppUser findAppUserByUsername(String username);
    public AppUser findAppUserByAddress(String address);
    public boolean existsAppUserByUsername(String username);



//    @Query("select u.code from Code u where AppUser.username=?1")
//    public int getActive(String email);
////
////    @Query("select u.password from AppUser u where AppUser.username=?1")
////    public String getPassword(String username);
}
