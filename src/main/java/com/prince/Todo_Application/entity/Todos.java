package com.prince.Todo_Application.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
//import jakarta.validation.constraints.Size;

@Entity
public class Todos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@Size(min = 10 , message = "Enter atleast 10 characters")
	private String description;
	private LocalDate startDate;
	private LocalDate completionDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Users user;

	public Todos() {
		super();
	}

	public Todos(int id, String description, LocalDate startDate, LocalDate completionDate, Users user) {
		super();
		this.id = id;
		this.description = description;
		this.startDate = startDate;
		this.completionDate = completionDate;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Todos [id=" + id + ", description=" + description + ", startDate=" + startDate + ", completionDate="
				+ completionDate + ", user=" + user + "]";
	}

}
