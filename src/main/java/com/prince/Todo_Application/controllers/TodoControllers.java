package com.prince.Todo_Application.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prince.Todo_Application.entity.Todos;
import com.prince.Todo_Application.exception.UserNotFoundException;
import com.prince.Todo_Application.services.TodoServices;

@RestController
@RequestMapping("/todos")
public class TodoControllers {

    private TodoServices todoServices;

    public TodoControllers(TodoServices todoServices) {
        this.todoServices = todoServices;
    }

    @GetMapping
    public List<Todos> getAllTodos() {
        return todoServices.getAllTodos();
    }

    @GetMapping("/user/{userId}")
    public List<Todos> getTodosByUser(@PathVariable int userId) {
        return todoServices.getTodosByUserId(userId);
    }
    
    @GetMapping("/user/{userId}/{todoId}")
    public ResponseEntity<Todos> findTodo(
            @PathVariable int userId,
            @PathVariable int todoId) {

        Todos todo = todoServices.findTodoByTodoId(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if(todo.getUserId() != userId){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(todo);
    }


    @PostMapping("/user/{userId}")
    public ResponseEntity<Object> createTodo(@PathVariable int userId, @RequestBody Todos todo) throws UserNotFoundException {
        return todoServices.createTodo(userId, todo);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTodoByTodoId(@PathVariable int id) {
    	return todoServices.deleteByTodoId(id);
    }
}
