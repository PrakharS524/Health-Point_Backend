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

import com.HealthPoint.Dto.UserManagementDto.LoginDto;
import com.HealthPoint.entity.UserManagementEntity.UserRegisterLoginEntity;
import com.HealthPoint.service.UserManagementService.LoginService;
import com.HealthPoint.util.session.GetValueFromSession;
import com.HealthPoint.util.session.SetValueInSession;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class LoginController {
	
	@Autowired
	private final LoginService loginService;
	
    @PostMapping(value = "/login", produces = "application/json") 
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDTO, BindingResult bindingResult, HttpSession session) {
    	List<String> errorList = new ArrayList<>();
        
    	//Handle validation errors
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }

        try {
            Map<String, Object> information = loginService.loginUser(loginDTO.getEmail(), loginDTO.getPassword());

            UserRegisterLoginEntity user = (UserRegisterLoginEntity) information.get("user");
            if (user != null) {
                //Set session attributes
                new SetValueInSession().setValueInSession(session, user.getName(), user.getEmail(), user.getUserName());

                Map<String, Object> sessionUserMap =new GetValueFromSession().getSessionUserMap(session);
                return ResponseEntity.ok(Map.of("user", sessionUserMap));
            } else {
            	errorList.add("Something went wrong, try again");
                return ResponseEntity.badRequest().body(Map.of("errors", errorList));
            }

        } catch (RuntimeException e) {
            errorList.add(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("errors", errorList));
        }
    }
}
