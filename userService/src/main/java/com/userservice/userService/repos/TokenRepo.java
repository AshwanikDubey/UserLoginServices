package com.userservice.userService.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.userService.models.Token;

@Repository
public interface TokenRepo extends JpaRepository<Token,Long> {
	Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String token,boolean isDeleted
			,long expiryTime);
}
