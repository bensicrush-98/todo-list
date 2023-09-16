package com.example.demo.dto.task;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class TaskCreationDTO {
    @NotNull
    @NotBlank(message = "Title cannot be empty.")
    private String title;
    @Size(max = 500, message = "Description should not exceed 500 characters.")
    private String description;
    @Future(message = "Due date must be in the future.")
    private LocalDateTime dueDate;

    public TaskCreationDTO(){

    }

    public TaskCreationDTO(String title, String description, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
