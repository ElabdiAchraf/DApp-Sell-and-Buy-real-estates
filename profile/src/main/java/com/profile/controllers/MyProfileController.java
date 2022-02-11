package com.profile.controllers;



import com.profile.dtos.ProfileInput;
import com.profile.entities.Profile;
import com.profile.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class MyProfileController {

    @Autowired
    ProfileService profileService;

    @PostMapping("/createProfile")
    public Profile createProfile(@RequestBody ProfileInput profileInput){
        return profileService.createProfile(profileInput);
    }
    @PutMapping("/updateProfile")
    public Profile updateProfile(@RequestBody Profile profileInput ){
        return profileService.updateProfile(profileInput);
    }
    @GetMapping("/myProfile")
    public Profile updateProfile(){
        return profileService.getProfile();
    }
}
