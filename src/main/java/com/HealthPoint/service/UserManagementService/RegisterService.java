package com.HealthPoint.service.UserManagementService;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HealthPoint.Dto.UserManagementDto.RegisterDto;
import com.HealthPoint.entity.UserManagementEntity.UserContactdetailsEntity;
import com.HealthPoint.entity.UserManagementEntity.UserRegisterLoginEntity;
import com.HealthPoint.repo.UserManagementRepo.UserRepo;

@Service
public class RegisterService {
	
	@Autowired
    private UserRepo userRegisterRepo;

	public boolean registerUserCheck(String email) {  	
	    //Check duplicate email
	    if (userRegisterRepo.existsByEmail(email)) {
	    	return true;
	    }
		return false;
	}
	
	@Transactional
	public String registerUser(RegisterDto dto) {
		String error = null;
	    if (userRegisterRepo.existsByEmail(dto.getEmail())) {
	    		error = "Email already exists";
	    }

	    //Create main user entity
	    UserRegisterLoginEntity userRegEntity = new UserRegisterLoginEntity();
	    userRegEntity.setName(dto.getName());
	    userRegEntity.setEmail(dto.getEmail());
	    userRegEntity.setUserName(dto.getUserName());
	    userRegEntity.setPassword(dto.getPassword()); 
	    userRegEntity.setJoiningDate(LocalDate.now());
	

	    //Create contact details entity
	    UserContactdetailsEntity contact = new UserContactdetailsEntity();
	    contact.setMobileNumber(dto.getMobileNumber());
	
	    //Map both entities
	    contact.setUserRegisterLoginEntity(userRegEntity);
	    userRegEntity.setUserContactId(contact);

	    //Save user
	    userRegisterRepo.save(userRegEntity);

	    return error;
	}
}
