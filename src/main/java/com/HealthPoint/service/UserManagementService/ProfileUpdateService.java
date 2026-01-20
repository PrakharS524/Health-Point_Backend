package com.HealthPoint.service.UserManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HealthPoint.Dto.UserManagementDto.ProfileUpdateDto;
import com.HealthPoint.entity.UserManagementEntity.UserContactdetailsEntity;
import com.HealthPoint.entity.UserManagementEntity.UserRegisterLoginEntity;
import com.HealthPoint.repo.UserManagementRepo.UserRepo;

@Service
@Transactional
public class ProfileUpdateService {
	
	@Autowired
    private UserRepo userRepo;
	
	 public String updateUserProfile(ProfileUpdateDto dto) {

        // 1. Find user by email
        UserRegisterLoginEntity user = userRepo.findByEmail(dto.getEmail()).orElse(null);
        if (user == null) {
            return "This email not exist, Email : " + dto.getEmail();
        }

        // 2. Get contact details
        UserContactdetailsEntity contact = user.getUserContactId();
        if (contact == null) {
            return "Somthing Wrong, Try agin";
        }

        // 3. Update register table
        user.setName(dto.getName());

        // 4. Update contact table
        contact.setMobileNumber(dto.getMobileNumber());
        contact.setStateName(dto.getStateName());
        contact.setDistrict(dto.getDistrict());
        contact.setCity(dto.getCity());
        contact.setAddress(dto.getAddress());

        // 5. Save
        userRepo.save(user);

        return null; // SUCCESS
    }
}
