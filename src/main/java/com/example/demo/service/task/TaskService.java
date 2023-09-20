package com.example.demo.service.task;

import com.example.demo.dto.task.TaskCreationDTO;
import com.example.demo.dto.task.TaskDTO;
import com.example.demo.dto.task.TaskUpdateDTO;

import java.util.List;

public interface TaskService {
    TaskDTO createTask(String token, TaskCreationDTO taskCreationDTO);

    List<TaskDTO> getAllTasksForUser(String token);
    TaskDTO updateTask(String token, TaskUpdateDTO taskDTO);
}
