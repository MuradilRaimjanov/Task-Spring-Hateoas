package com.example.taskspringhateostaskservice.service;

import com.example.taskspringhateostaskservice.model.Task;

import java.util.List;

public interface TaskService {
    Task getById(Long id);

    List<Task> getAllByUserEmail(String userEmail);

    Task create(Task task);

    Task update(Task task, Long taskId);

    void delete(Long id);
}
