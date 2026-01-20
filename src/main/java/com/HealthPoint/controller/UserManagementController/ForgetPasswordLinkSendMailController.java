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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HealthPoint.Dto.UserManagementDto.ForgetPasswordLinkSendMailDao;
import com.HealthPoint.service.UserManagementService.ForgetPasswordLinkSendMailService;
import com.HealthPoint.util.mail.ForgetPassWordLinkSendInMail;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ForgetPasswordLinkSendMailController {
	
	@Autowired
	private final ForgetPasswordLinkSendMailService userFetchService;
	
	@PostMapping("/forget-password-link-send")
    public ResponseEntity<?> forgetPasswordSendLinkInMail(@Valid @RequestBody ForgetPasswordLinkSendMailDao dto, BindingResult bindingResult, HttpSession session) {		
		List<String> errorList = new ArrayList<>();
        
		// Validation errors
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
        
        String userName = userFetchService.getUserNameByEmail(dto);
        
        
        if (userName == null) {
        	errorList.add("This email not exist, Try again");
            return ResponseEntity.badRequest().body(Map.of("error", errorList));
        }
        boolean forgetPassWordLinkSendInMail = new ForgetPassWordLinkSendInMail().forgetPassWordLinkSendInMail(userName, dto.getEmail(), userName);
        
        if(forgetPassWordLinkSendInMail) {
        	return ResponseEntity.ok(Map.of("message", "Check email to send link for create new password"));    	
        }else {
        	errorList.add("Failed forget password, Try again");
            return ResponseEntity.badRequest().body(Map.of("error", errorList));

        }
    }

}
