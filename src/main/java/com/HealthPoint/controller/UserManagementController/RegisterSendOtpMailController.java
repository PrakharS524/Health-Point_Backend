package com.HealthPoint.controller.UserManagementController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.HealthPoint.Dto.UserManagementDto.RegisterDto;
import com.HealthPoint.Dto.UserManagementDto.RegisterSendOtpMailDto;
import com.HealthPoint.service.UserManagementService.RegisterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RegisterSendOtpMailController {
	
	@Autowired
    private final RegisterService registerService;
    
    @PostMapping("/sendOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody RegisterSendOtpMailDto otpDTO, BindingResult bindingResult, HttpSession session) {
    	String error1 = null;
    	List<String> errorList = new ArrayList<>();
    	

    	//Handle validation errors
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
        
	    	String sessionOtp = (String) session.getAttribute("userOtp");
	    	RegisterDto registerDTO = new RegisterDto();
	        
	    	registerDTO.setName((String) session.getAttribute("name"));
	    	registerDTO.setEmail((String) session.getAttribute("email"));
	    	registerDTO.setUserName((String) session.getAttribute("userName"));
	    	registerDTO.setMobileNumber((String) session.getAttribute("phoneNumber"));
	    	registerDTO.setPassword((String) session.getAttribute("userPassword"));
	    	registerDTO.setConfirmPassword((String) session.getAttribute("userPassword"));
        
        if (!sessionOtp.equals(otpDTO.getOtp())) {
        	errorList.add("You entered wrong OTP, try again");
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
        // Call service layer
        try {
        		error1 = registerService.registerUser(registerDTO);
        }catch(Exception e){
        		error1 = "register is failed, Please Try Again";
        }
        
        session.removeAttribute("userPassword");
        session.removeAttribute("userOtp");
        
        if(error1!=null) {
	    		errorList.add("this email already exixst, click to login");
	        	return ResponseEntity.badRequest().body(Map.of("errors", errorList));
    		}else {
    		return ResponseEntity.ok(Map.of("message", "User registered successfully"));    		
    	}
    }
}
