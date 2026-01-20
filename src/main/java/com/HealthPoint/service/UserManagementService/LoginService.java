package com.HealthPoint.service.UserManagementService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HealthPoint.entity.UserManagementEntity.UserContactdetailsEntity;
import com.HealthPoint.entity.UserManagementEntity.UserRegisterLoginEntity;
import com.HealthPoint.repo.UserManagementRepo.UserRepo;

@Service
public class LoginService {
	@Autowired
    private UserRepo userRegisterRepo;
	
	public Map<String, Object> loginUser(String email, String password) {
	   String state=null, district=null, cityVillage=null;
	   Map<String, Object> responseMap = new HashMap<>();
	   
	   Optional<UserRegisterLoginEntity> optionalUser =userRegisterRepo.findByEmailAndPassword(email, password);

        if (optionalUser.isPresent()) {
        	UserRegisterLoginEntity userLoginInfo=optionalUser.get();
        	UserContactdetailsEntity usercontactInfo=userLoginInfo.getUserContactId();
        	state=usercontactInfo.getStateName();
        	district=usercontactInfo.getDistrict();
        	cityVillage=usercontactInfo.getCity();
        	
        	responseMap.put("user", userLoginInfo);
        	return responseMap;
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

}
