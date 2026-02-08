package com.prince.Todo_Application.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prince.Todo_Application.entity.Todos;
import com.prince.Todo_Application.exception.UserNotFoundException;
import com.prince.Todo_Application.repository.TodosJpa;

@Service
public class TodoServices {

    private TodosJpa repository;
    private RestTemplate restTemplate;

    public TodoServices(TodosJpa repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public List<Todos> getAllTodos() {
        return repository.findAll();
    }

    public List<Todos> getTodosByUserId(int userId) {
        return repository.findByUserId(userId);
    }
    
    public Optional<Todos> findTodoByTodoId(int id) {
    	return repository.findById(id);
    }

    public ResponseEntity<Object> createTodo(int userId, Todos todo) throws UserNotFoundException {
        String userServiceUrl = "http://localhost:8080/users/" + userId;

        try {
            restTemplate.getForObject(userServiceUrl, Object.class);
        } catch (Exception e) {
            throw new UserNotFoundException("User not found with id : " + userId);
        }

        todo.setUserId(userId);
        Todos savedTodo = repository.save(todo);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTodo.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    
    public ResponseEntity<Object> deleteByTodoId(int id) {
        Todos todo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        repository.delete(todo);
        return ResponseEntity.noContent().build();
    }

	public ResponseEntity<Todos> updateTodo(int id, Todos todo) {
		Todos exsitingTodo =  repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Todo not Found"));
		
		if(todo.getDescription() != null) {
		    exsitingTodo.setDescription(todo.getDescription());
	    }
		
		if(todo.getStartDate() != null) {
		    exsitingTodo.setStartDate(todo.getStartDate());
	    }
		
		if(todo.getCompletionDate() != null) {
		    exsitingTodo.setCompletionDate(todo.getCompletionDate());
	    }
		
		Todos updatedTodo = repository.save(exsitingTodo);
		
		return ResponseEntity.ok(updatedTodo);
	}

}

