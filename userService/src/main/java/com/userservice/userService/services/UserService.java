package com.userservice.userService.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.userservice.userService.models.Token;
import com.userservice.userService.models.User;
import com.userservice.userService.repos.TokenRepo;
import com.userservice.userService.repos.UserRepo;
@Service
public class UserService {
	private UserRepo userRepo;
	private BCryptPasswordEncoder passwordEncoder;
	private TokenRepo tokenRepo;
	//here UserRepo and BCryptPasswordEncoder class inject to UserService 
	public UserService(UserRepo userRepo,BCryptPasswordEncoder passwordEncoder,TokenRepo tokenRepo) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.tokenRepo = tokenRepo;
	}
	
	public User signUp(String name, String email,String password) {
		// Add Validation
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setHashedPassword(passwordEncoder.encode(password));//Here encrypt password
		//user having encrypt password and all other details
		return userRepo.save(user);
		
	}
	
	public Token login(String email,String password) {
		Optional<User> optionalUser = userRepo.findByEmail(email);
		if(optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("No User Present ");
		}
		User user = optionalUser.get();
		if(!passwordEncoder.matches(password, user.getHashedPassword())) {
			throw new UsernameNotFoundException("User name and email not matched !!!");
		}
		Token token = generateToken(user);
		return tokenRepo.save(token);
		
	}

	private Token generateToken(User user) {
		 Token token = new Token();
		 token.setValue(UUID.randomUUID().toString());
		 token.setExpiryAt(System.currentTimeMillis()+3600000);
		 token.setUser(user);
		return token;
	}

	public User validateToken(String token) {
		/*
		 A token is valid if
		 1.Token Exist in DB
		 2.Token has not expired
		 3.Token has not marked as deleted
		 */
		Optional<Token> tokenResult = tokenRepo.findByValueAndDeletedAndExpiryAtGreaterThan
				(token,false,System.currentTimeMillis());
		if(tokenResult.isEmpty()) {
			return null;
		}
		return tokenResult.get().getUser();
	}
}
