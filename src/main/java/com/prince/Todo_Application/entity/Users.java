package com.prince.Todo_Application.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//import jakarta.validation.constraints.Size;


@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@Size(min = 3, message = "Size mustbe of atleast 3 characters")
	private String name;
	
	@OneToMany(mappedBy = "user")
	private List<Todos> todoList;

	public Users() {
		super();
	}

	public Users(int id, String name, List<Todos> todoList) {
		super();
		this.id = id;
		this.name = name;
		this.todoList = todoList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Todos> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<Todos> todoList) {
		this.todoList = todoList;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + "]";
	}

}
