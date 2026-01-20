package com.HealthPoint.repo.UserManagementRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HealthPoint.entity.UserManagementEntity.UserRegisterLoginEntity;

@Repository
public interface UserRepo extends JpaRepository<UserRegisterLoginEntity, Integer> {
   
	boolean existsByEmail(String email); // check duplicate email
    
    Optional<UserRegisterLoginEntity> findByEmailAndPassword(String email, String password);
    
    Optional<UserRegisterLoginEntity> findByEmail(String email);

}

