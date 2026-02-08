package com.prince.Todo_Application.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Todos {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @NotBlank(message = "Description is required")
	    @Size(min = 10, message = "Enter atleast 10 characters")
	    private String description;

	    @NotNull(message = "Start date is required")
	    private LocalDate startDate;

	    @NotNull(message = "Completion date is required")
	    private LocalDate completionDate;

	    private Integer userId;

    public Todos() {}

    public Todos(int id, String description, LocalDate startDate, LocalDate completionDate, int userId) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.completionDate = completionDate;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

