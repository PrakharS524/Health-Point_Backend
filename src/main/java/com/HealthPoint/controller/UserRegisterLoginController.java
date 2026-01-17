package com.HealthPoint.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import org.springframework.web.bind.annotation.*;

import com.HealthPoint.Dto.SendOtpDto;
import com.HealthPoint.Dto.UserLoginDTO;
import com.HealthPoint.Dto.UserRegisterDTO;
import com.HealthPoint.entity.UserContactdetailsEntity;
import com.HealthPoint.entity.UserRegisterLoginEntity;
import com.HealthPoint.service.UserRegisterLoginService;
import com.HealthPoint.util.Send_mail;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRegisterLoginController {

    private final UserRegisterLoginService userRegisterService;
    
    
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> sendOtp(@Valid @RequestBody UserRegisterDTO userRegisterDTO, BindingResult bindingResult,
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
            if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
                return ResponseEntity.badRequest()
                        .body(Map.of("errors", "Password and confirmPassword do not match"));
            }else {
            	boolean result= userRegisterService.registerUserCheck(userRegisterDTO.getEmail());
            	if(result==true) {
            		return ResponseEntity.badRequest()
                            .body(Map.of("errors", "You are already register,click to login"));
            	}else {
	            	int otp = (int) (Math.random() * 900000) + 100000;
	            	String otp1=otp+"";
	            	Send_mail send_mail = new Send_mail();
	                send_mail.mail_information(userRegisterDTO.getName(), userRegisterDTO.getEmail(), otp);
	            	
	                session.setAttribute("name", userRegisterDTO.getName());
	                session.setAttribute("email", userRegisterDTO.getEmail());
	                session.setAttribute("userName", userRegisterDTO.getPassword());	 
	                session.setAttribute("phoneNumber", userRegisterDTO.getMobileNumber());
	                session.setAttribute("userPassword", userRegisterDTO.getPassword());
	                session.setAttribute("userOtp",otp1 );
	
	                Map<String, Object> sessionUserMap = getSessionUserMap(session);
	                return ResponseEntity.ok(Map.of(
	                        "user", sessionUserMap
	                   ));
	             }
            }
        } catch (RuntimeException e) {
            errorList.add(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
    	
    }
    
          
    @PostMapping("/sendOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody SendOtpDto otpDTO, HttpSession session) {
    	
    	List<String> errorList = new ArrayList<>();
    	
    	String sessionOtp = (String) session.getAttribute("userOtp");
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        
        userRegisterDTO.setName((String) session.getAttribute("name"));
        userRegisterDTO.setEmail((String) session.getAttribute("email"));
        userRegisterDTO.setUserName((String) session.getAttribute("userName"));
        userRegisterDTO.setMobileNumber((String) session.getAttribute("phoneNumber"));
        userRegisterDTO.setPassword((String) session.getAttribute("userPassword"));
        userRegisterDTO.setConfirmPassword((String) session.getAttribute("userPassword"));
        
        
        System.out.println("=======================2========");
        if (!sessionOtp.equals(otpDTO.getOtp())) {
            return ResponseEntity.ok(Map.of(
                    "errors", "You entered wrong OTP, try again"
            ));
        }
        // Call service layer
        try {
        	Map<String, Object> information = userRegisterService.registerUser(userRegisterDTO);
        	session.removeAttribute("userPassword");
        }catch(Exception e){
        	return ResponseEntity.ok(Map.of(
                    "errors", "this email already exixst, click to login"
            ));
        }
        
        // Remove OTP from session
        session.removeAttribute("userOtp");
        return ResponseEntity.ok(Map.of(
                "message", "User registered successfully"
        ));
    }

    
    
    
    
    
    


    // ------------------- LOGIN -------------------
    @PostMapping(value = "/login", produces = "application/json") 
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO, BindingResult bindingResult, HttpSession session) {
        List<String> errorList = new ArrayList<>();
        //Handle validation errors
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }

        try {
            Map<String, Object> information = userRegisterService.loginUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());

            UserRegisterLoginEntity user = (UserRegisterLoginEntity) information.get("user");
            if (user != null) {
                UserContactdetailsEntity userContactId = user.getUserContactId();

                //Set session attributes
                session.setAttribute("name", user.getName());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("userName", user.getUserName());	 
                session.setAttribute("phoneNumber", userContactId.getMobileNumber());

                Map<String, Object> sessionUserMap = getSessionUserMap(session);
                return ResponseEntity.ok(Map.of(
                        "message", "Login successful"
                ));
            } else {
                return ResponseEntity.badRequest().body(Map.of(
                        "errors", "Something went wrong, try again"
                ));
            }

        } catch (RuntimeException e) {
            errorList.add(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
    }
 
    private Map<String, Object> getSessionUserMap(HttpSession session) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", session.getAttribute("name"));
        userMap.put("email", session.getAttribute("email"));
        userMap.put("userName", session.getAttribute("userName"));
        userMap.put("phoneNumber", session.getAttribute("phoneNumber"));
        return userMap;
    }
}
