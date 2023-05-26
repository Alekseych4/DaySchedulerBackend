package com.example.dayschedulerbackend.service;

import com.example.dayschedulerbackend.domain.dto.TaskDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskDto getById(UUID taskId);
    List<TaskDto> getTasksByUserId(UUID userId);
    void create(TaskDto taskDto);
    void update(TaskDto taskDto);
    void deleteById(UUID id);
}
