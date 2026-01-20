package com.HealthPoint.controller.UserManagementController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import org.springframework.web.bind.annotation.*;

import com.HealthPoint.Dto.UserManagementDto.RegisterDto;
import com.HealthPoint.service.UserManagementService.RegisterService;
import com.HealthPoint.util.mail.UserRegisterSendOtpInMail;
import com.HealthPoint.util.session.GetValueFromSession;
import com.HealthPoint.util.session.SetValueInSession;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class RegisterController {

	@Autowired
    private final RegisterService registerService;
   
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> sendOtp(@Valid @RequestBody RegisterDto registerDto, BindingResult bindingResult,
            HttpSession session) {
       
    	List<String> errorList = new ArrayList<>();
        // Validation errors
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }

        try {
            //Password match check
            if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            	errorList.add("Password and confirmPassword do not match");
                return ResponseEntity.badRequest().body(Map.of("errors", errorList));
            }else {
            	boolean result= registerService.registerUserCheck(registerDto.getEmail());
            	if(result==true) {
            		errorList.add("You are already register,click to login");
            		return ResponseEntity.badRequest().body(Map.of("errors", errorList));
            	}else {
	            	int otp = (int) (Math.random() * 900000) + 100000;
	            	String otp1=otp+"";
	            	UserRegisterSendOtpInMail sendMail = new UserRegisterSendOtpInMail();
	            	boolean userRegisterSendOtpInMail = sendMail.userRegisterSendOtpInMail(registerDto.getName(), registerDto.getEmail(), otp);
	            	
	            	if(userRegisterSendOtpInMail) {
	            		new SetValueInSession().setValueInSession(session, registerDto.getName(), registerDto.getEmail(), registerDto.getUserName());
	            		
	            		session.setAttribute("phoneNumber", registerDto.getMobileNumber());
					session.setAttribute("userPassword", registerDto.getPassword());
					session.setAttribute("userOtp",otp1 );
	
		                Map<String, Object> sessionUserMap =new GetValueFromSession().getSessionUserMap(session);
		                return ResponseEntity.ok(Map.of("user", sessionUserMap));
	            	}else {
	            		errorList.add("Register failed try again");
	                    return ResponseEntity.badRequest().body(Map.of("errors", errorList));
	            	}
	             }
            }
        } catch (RuntimeException e) {
            errorList.add(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
    	
    }
}
