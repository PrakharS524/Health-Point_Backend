package com.HealthPoint.Dto.UserManagementDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterSendOtpMailDto {
	@NotBlank(message = "Enter otp , blank not allow")
    @Size(min = 6, max = 6, message = "Otp only allow 6 digit")
    private String otp;

}
