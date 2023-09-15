package com.simplificado.pic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplificado.pic.dto.UserDto;
import com.simplificado.pic.service.UserService;
import com.simplificado.pic.user.User;

@RestController()
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User>createUser(@RequestBody UserDto dto){
		User newUser = userService.createUser(dto);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> listUsers =  this.userService.getAllUser();
		return new ResponseEntity<> (listUsers, HttpStatus.OK);
	}
}
