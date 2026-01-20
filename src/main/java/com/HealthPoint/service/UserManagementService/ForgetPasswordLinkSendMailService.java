package com.HealthPoint.service.UserManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthPoint.Dto.UserManagementDto.ForgetPasswordLinkSendMailDao;
import com.HealthPoint.entity.UserManagementEntity.UserRegisterLoginEntity;
import com.HealthPoint.repo.UserManagementRepo.UserRepo;

@Service
public class ForgetPasswordLinkSendMailService {
	
	@Autowired
	private UserRepo userRepo;
	
	public String getUserNameByEmail(ForgetPasswordLinkSendMailDao dto) {
		
		UserRegisterLoginEntity user =userRepo.findByEmail(dto.getEmail()).orElse(null);
	
	    if (user == null) {
	        return null; // user not found
	    }	
	    return user.getUserName(); // return user_name
	}

}
