package com.profile.services;



import com.profile.dtos.ProfileInput;
import com.profile.entities.Profile;

import java.math.BigInteger;


public interface ProfileService {

   public Profile createProfile(ProfileInput profileInput);

   public Profile updateProfile( Profile profileInput);

   public String deleteProfile(Long id);

   public Profile getProfile();
}
