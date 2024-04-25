package com.example.taskspringhateostaskservice.web.controller;
import com.example.taskspringhateostaskservice.model.Task;
import com.example.taskspringhateostaskservice.service.TaskService;
import com.example.taskspringhateostaskservice.web.dto.TaskDTO;
import com.example.taskspringhateostaskservice.web.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("/{id}")
    public TaskDTO getById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @GetMapping("/{email}")
    public List<TaskDTO> getAllByUserEmail(@PathVariable("email") String userEmail) {
        List<Task> tasks = taskService.getAllByUserEmail(userEmail);
        return taskMapper.toDto(tasks);
    }

    @PutMapping("/update/{taskId}")
    public TaskDTO update(@RequestBody TaskDTO dto, @PathVariable Long taskId) {
        Task task = taskMapper.toEntity(dto);
        return taskMapper.toDto(taskService.update(task, taskId));
    }

    @PostMapping("/create")
    public TaskDTO create(@RequestBody TaskDTO dto) {
        Task task = taskMapper.toEntity(dto);
        return taskMapper.toDto(taskService.create(task));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
