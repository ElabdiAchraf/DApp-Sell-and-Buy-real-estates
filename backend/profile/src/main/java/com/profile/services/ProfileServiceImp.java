package com.profile.services;


import com.profile.dtos.ProfileInput;
import com.profile.dtos.UserExistDto;
import com.profile.dtos.UserResp;
import com.profile.entities.Profile;
import com.profile.repositories.ProfileRepository;
import com.profile.restClient.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImp implements ProfileService{
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    UserRestClient userRestClient;


    @Override
    public Profile createProfile(ProfileInput profileInput) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserExistDto userExistDto = new UserExistDto();
        userExistDto.setUsername((String) auth.getPrincipal());
        UserResp userResp = userRestClient.getUser(userExistDto);
        Profile profile = new Profile(
                null,
                profileInput.getFirstName(),
                profileInput.getLastName(),
                profileInput.getAddress(),
                profileInput.getPhoto(),
                profileInput.getPhone(),
                userResp.getId()
        );
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile( Profile profileInput) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserExistDto userExistDto = new UserExistDto();
        userExistDto.setUsername((String) auth.getPrincipal());
        UserResp userResp = userRestClient.getUser(userExistDto);
        Profile profile = profileRepository.findProfileByUserId(userResp.getId());
        profile.setFirstName(profileInput.getFirstName());
        profile.setLastName(profileInput.getLastName());
        profile.setAddress(profileInput.getAddress());
        profile.setPhone(profileInput.getPhone());
        profile.setPhoto(profileInput.getPhoto());
        return profileRepository.save(profile);
    }

    @Override
    public String deleteProfile(Long id) {
        Profile profile = profileRepository.findProfileByUserId(id);

                profileRepository.delete(profile);
        return "profile deleted";
    }
    @Override
    public Profile getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserExistDto userExistDto = new UserExistDto();
        userExistDto.setUsername((String) auth.getPrincipal());
        UserResp userResp = userRestClient.getUser(userExistDto);
        return profileRepository.findProfileByUserId(userResp.getId());
    }
}
