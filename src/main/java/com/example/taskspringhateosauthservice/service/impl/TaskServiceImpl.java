package com.example.taskspringhateostaskservice.service.impl;

import com.example.taskspringhateostaskservice.model.Status;
import com.example.taskspringhateostaskservice.model.Task;
import com.example.taskspringhateostaskservice.model.exception.TaskNotFoundException;
import com.example.taskspringhateostaskservice.repository.TaskRepository;
import com.example.taskspringhateostaskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with this id not found!"));
    }

    @Override
    public List<Task> getAllByUserEmail(String userEmail) {
        return taskRepository.getAllByUserEmail(userEmail).orElseThrow(() -> new TaskNotFoundException("Task with this user email not found!"));
    }

    @Override
    @Transactional
    public Task create(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.TODO);
        }
        task.setExpirationDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task update(Task task, Long taskId) {
        Task taskBD = taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("Task not found"));
        taskBD.setTitle(task.getTitle());
        taskBD.setDescription(task.getDescription());
        return taskRepository.save(taskBD);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
