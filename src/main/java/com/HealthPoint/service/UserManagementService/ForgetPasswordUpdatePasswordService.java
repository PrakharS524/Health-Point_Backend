package com.HealthPoint.service.UserManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthPoint.Dto.UserManagementDto.ForgetPasswordUpdatePasswordDto;
import com.HealthPoint.entity.UserManagementEntity.UserRegisterLoginEntity;
import com.HealthPoint.repo.UserManagementRepo.UserRepo;

@Service
public class ForgetPasswordUpdatePasswordService {
	 @Autowired
	 private UserRepo userRepo;

	 public String updatePassword(ForgetPasswordUpdatePasswordDto dto) {

	        // 1️⃣ Find user by email
	        UserRegisterLoginEntity user = userRepo.findByEmail(dto.getEmail()).orElse(null);

	        if (user == null) {
	            return "This email not exist, Register First";
	        }

	        //Update only password
	        user.setPassword(dto.getNewPassword());

	        //Save
	        userRepo.save(user);

	        return null; // success
	    }
}
