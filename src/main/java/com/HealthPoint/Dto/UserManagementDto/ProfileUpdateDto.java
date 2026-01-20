package com.HealthPoint.Dto.UserManagementDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProfileUpdateDto {
	@NotBlank(message = "Email is required")
	@Pattern(
        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
        message = "Invalid email format"
	 )
	private String email;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 100)
    private String name;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 100)
    private String address;

    @NotBlank(message = "City cannot be blank")
    @Size(max = 50)
    private String city;

    @NotBlank(message = "District cannot be blank")
    @Size(max = 50)
    private String district;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "State name cannot be blank")
    @Size(max = 50)
    private String stateName;

}
