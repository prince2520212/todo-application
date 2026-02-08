package com.prince.Todo_Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prince.Todo_Application.entity.Todos;

public interface TodosJpa extends JpaRepository<Todos, Integer>{

	List<Todos> findByUserId(int userId);

}
