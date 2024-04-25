package com.example.taskspringhateostaskservice.web.mapper;

import com.example.taskspringhateostaskservice.model.Task;
import com.example.taskspringhateostaskservice.web.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TaskMapper extends Mappable<Task, TaskDTO> {

}