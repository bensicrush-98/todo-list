package com.example.demo.controller;

import com.example.demo.dto.task.TaskCreationDTO;
import com.example.demo.dto.task.TaskDTO;
import com.example.demo.dto.task.TaskUpdateDTO;
import com.example.demo.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestHeader("Authorization") String token, @Valid @RequestBody TaskCreationDTO taskCreationDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            List<String> errorMessages = new ArrayList<>();
            for(ObjectError error : bindingResult.getAllErrors()){
                errorMessages.add(error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }


        TaskDTO createdTask = taskService.createTask(token.split("Bearer")[1],taskCreationDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks(@RequestHeader("Authorization") String token){
       List<TaskDTO> userTasks = taskService.getAllTasksForUser(token.split("Bearer")[1]);
        return new ResponseEntity<>(userTasks, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<TaskDTO> updateTask(@RequestHeader("Authorization") String token, @RequestBody TaskUpdateDTO taskUpdateDTO){
        TaskDTO updatedTask = taskService.updateTask(token.split("Bearer")[1],taskUpdateDTO);
        return new ResponseEntity<TaskDTO>(updatedTask,HttpStatus.OK);
    }



}
