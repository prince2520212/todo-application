package com.prince.Todo_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prince.Todo_Application.entity.Todos;

public interface TodosJpa extends JpaRepository<Todos, Integer>{

}
