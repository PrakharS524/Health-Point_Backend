package com.HealthPoint.entity.UserManagementEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "UserContactDetails")
public class UserContactdetailsEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userContactId;
	
	
	@Column(name = "mobile_number", length = 10, nullable = false )
	private String mobileNumber;
	
	@Column(name = "stateName", length = 50, nullable = true)
	private String stateName;
	
	@Column(name = "district", length = 50, nullable = true)
	private String district;
	
	@Column(name = "city", length = 50, nullable = true)
	private String city;
	
	@Column(name = "address",length = 100, nullable = true)
	private String address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	@JsonIgnore
	private UserRegisterLoginEntity userRegisterLoginEntity;	
}