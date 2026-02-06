package com.prince.Todo_Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prince.Todo_Application.entity.Users;

public interface UsersJpa extends JpaRepository<Users, Integer> {

}
