package com.prince.Todo_Application.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prince.Todo_Application.entity.Users;
import com.prince.Todo_Application.exception.UserNotFoundException;
import com.prince.Todo_Application.services.UserServices;

@RestController
public class UserControllers {
	
	private UserServices services;

	public UserControllers(UserServices services) {
		super();
		this.services = services;
	}
	
	
	@GetMapping("/users")
	public List<Users> retrieveAll(){
		return services.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public Users getOneUser(@PathVariable int id) throws UserNotFoundException {	
		return services.findOneUser(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody Users user) {
		return services.createUser(user);

	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) throws UserNotFoundException {
		return services.deleteUser(id);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Users> upadateUser(@PathVariable int id ,
			@RequestBody Users user) throws UserNotFoundException {
		return services.updateUser(id, user);
	}
	
	

}
