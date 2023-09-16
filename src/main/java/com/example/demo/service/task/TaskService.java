package com.example.demo.service.task;

import com.example.demo.dto.task.TaskCreationDTO;
import com.example.demo.dto.task.TaskDTO;

import java.util.List;

public interface TaskService {
    TaskDTO createTask(String token, TaskCreationDTO taskCreationDTO);

    List<TaskDTO> getAllTasksForUser(String token);
}
