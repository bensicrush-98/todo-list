package com.example.demo.dto.task;

import com.example.demo.entity.Task;

import java.time.LocalDateTime;
import java.util.Optional;

public class TaskUpdateDTO {
    private Long id;
    private Optional<String> title = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<LocalDateTime> dueDate = Optional.empty();
    private Optional<Task.TaskStatus> status = Optional.empty();

    public TaskUpdateDTO(Long id, Optional<String> title, Optional<String> description, Optional<LocalDateTime> dueDate, Optional<Task.TaskStatus> status) {
       this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }
    public Long getId() {
        return id;
    }

    public Optional<String> getTitle() {
        return title;
    }

    public void setTitle(Optional<String> title) {
        this.title = title;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    public Optional<LocalDateTime> getDueDate() {
        return dueDate;
    }

    public void setDueDate(Optional<LocalDateTime> dueDate) {
        this.dueDate = dueDate;
    }

    public Optional<Task.TaskStatus> getTaskStatus() {
        return status;
    }

    public void setTaskStatus(Optional<Task.TaskStatus> taskStatus) {
        this.status = taskStatus;
    }
}
