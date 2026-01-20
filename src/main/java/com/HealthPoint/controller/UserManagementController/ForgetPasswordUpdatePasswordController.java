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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HealthPoint.Dto.UserManagementDto.ForgetPasswordUpdatePasswordDto;
import com.HealthPoint.service.UserManagementService.ForgetPasswordUpdatePasswordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ForgetPasswordUpdatePasswordController {
	
	@Autowired
	private final ForgetPasswordUpdatePasswordService resetPasswordService;

    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ForgetPasswordUpdatePasswordDto dto, BindingResult bindingResult, HttpSession session) {
    	List<String> errorList = new ArrayList<>();
        
		// Validation errors
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
      
        //password match check
        if (!(dto.getNewPassword().equals(dto.getConfirmPassword()))) {
        	errorList.add("New password and confirm password do not match");
            return ResponseEntity.badRequest().body(Map.of("error", errorList));
        }

        //service call
        String error = resetPasswordService.updatePassword(dto);

        if (error != null) {
        	errorList.add(error);
            return ResponseEntity.badRequest().body(Map.of("error", errorList));
        }

        return ResponseEntity.ok(Map.of("message", "Password updated successfully"));
    }
}