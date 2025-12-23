package com.userservice.userService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.userService.dtos.LoginRequestDto;
import com.userservice.userService.dtos.SignUpRequestDto;
import com.userservice.userService.dtos.UserResponseDto;
import com.userservice.userService.models.Token;
import com.userservice.userService.models.User;
import com.userservice.userService.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService userService;

	UserController(UserService userService) {
		this.userService = userService;
	}

	// Login , Registration, SignUp
	// Sign-Up APi
	@PostMapping("/signUp")
	public UserResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
		User user = userService.signUp(signUpRequestDto.getName(), signUpRequestDto.getEmail(),
				signUpRequestDto.getPassword());

		return UserResponseDto.from(user);
	}

	@PostMapping("/login")
	public Token login(@RequestBody LoginRequestDto loginRequestDto) {
		return userService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
	}
	
	@GetMapping("/validate/{token}")
	public UserResponseDto validateToken(@PathVariable String token) {
		User user = userService.validateToken(token);
		return UserResponseDto.from(user);
	}

}
