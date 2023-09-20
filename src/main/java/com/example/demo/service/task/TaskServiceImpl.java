package com.example.demo.service.task;

import com.example.demo.auth.JwtProvider;
import com.example.demo.constants.ErrorMessages;
import com.example.demo.dto.task.TaskCreationDTO;
import com.example.demo.dto.task.TaskDTO;
import com.example.demo.dto.task.TaskUpdateDTO;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.exceptions.task.InvalidTaskFieldException;
import com.example.demo.exceptions.task.TaskNotFoundException;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.user.UserResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements  TaskService{

    private final UserResolver userResolver;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(UserResolver userResolver, TaskRepository taskRepository){
        this.userResolver = userResolver;
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDTO createTask(String token, TaskCreationDTO taskCreationDTO) {
        User user = userResolver.getUserFromToken(token);
        Task task = new Task();
        task.setTitle(taskCreationDTO.getTitle());
        task.setDueDate(taskCreationDTO.getDueDate());
        task.setDescription(taskCreationDTO.getDescription());
        task.setUser(user);

        Task savedTask = taskRepository.save(task);
        return  TaskMapper.toDTO(savedTask);
    }

    @Override
    public List<TaskDTO> getAllTasksForUser(String token){
        User user = userResolver.getUserFromToken(token);
        List<Task> tasks = taskRepository.findAllByUser(user);
        return tasks.stream().map(TaskMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateTask(String token, TaskUpdateDTO taskUpdateDTO){
        userResolver.getUserFromToken(token);
        Task task = taskRepository.findById(taskUpdateDTO.getId()).orElseThrow(TaskNotFoundException::new);
        taskUpdateDTO.getTitle().ifPresent(task::setTitle);
        taskUpdateDTO.getDescription().ifPresent(task::setDescription);
        taskUpdateDTO.getDueDate().ifPresent(task::setDueDate);
        taskUpdateDTO.getTaskStatus().ifPresent(task::setStatus);
        taskRepository.save(task);
        return TaskMapper.toDTO(task);
    }
}