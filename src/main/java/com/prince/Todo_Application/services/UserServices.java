package com.prince.Todo_Application.services;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prince.Todo_Application.entity.Users;
import com.prince.Todo_Application.exception.UserNotFoundException;
import com.prince.Todo_Application.repository.UsersJpa;

@Service
public class UserServices {
	
	private UsersJpa repository;

	public UserServices(UsersJpa repository) {
		super();
		this.repository = repository;
	}
	
	public List<Users> getAllUsers(){
		return repository.findAll();
	}
	
	public Users findOneUser(int id) throws UserNotFoundException {
		return repository.findById(id).orElseThrow(() -> 
		new UserNotFoundException("user with id : "+id+" not present"));
	}
	
	public ResponseEntity<Void> createUser(Users user) {
		Users savedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

	public ResponseEntity<Void> deleteUser(int id) throws UserNotFoundException {
		findOneUser(id);
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Users> updateUser(int id, Users user) throws UserNotFoundException {

	    Users existingUser = repository.findById(id)
	            .orElseThrow(() -> new UserNotFoundException("user with id : " + id + " not present"));
	    if(user.getName() != null) {
		    existingUser.setName(user.getName());
	    }
	    Users updatedUser = repository.save(existingUser);

	    return ResponseEntity.ok(updatedUser);
	}


}
