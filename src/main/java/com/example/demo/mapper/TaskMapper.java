package com.example.demo.mapper;

import com.example.demo.dto.task.TaskDTO;
import com.example.demo.entity.Task;

public class TaskMapper {
    public static TaskDTO toDTO(Task task){
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getStatus()
        );
    }
}
