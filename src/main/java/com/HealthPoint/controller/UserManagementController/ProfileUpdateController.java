package com.HealthPoint.controller.UserManagementController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HealthPoint.Dto.UserManagementDto.ProfileUpdateDto;
import com.HealthPoint.service.UserManagementService.ProfileUpdateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ProfileUpdateController {
	
	private final ProfileUpdateService updateProfileService;

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody ProfileUpdateDto dto, BindingResult bindingResult, HttpSession session) {
    	List<String> errorList = new ArrayList<>();
        // Validation errors
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
    	String error = updateProfileService.updateUserProfile(dto);
    	 
    	if(error!=null) {
    		errorList.add(error);
    		return ResponseEntity.badRequest().body(Map.of("errors", errorList));
    	}else {
    		return ResponseEntity.ok(Map.of("message", "User profile updated successfully"));   		 
    	 }
    	
    	
    }
}