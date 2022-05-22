package com.example.dayschedulerbackend.controller;

import com.example.dayschedulerbackend.domain.dto.TaskDto;
import com.example.dayschedulerbackend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/by-task-id/{taskId}")
    public TaskDto getTaskById(@PathVariable("taskId") UUID taskId) {
        return taskService.getById(taskId);
    }

    @GetMapping("/tasks-by-user-id/{userId}")
    public List<TaskDto> getTasksByUserId(@PathVariable("userId") UUID userId) {
        return taskService.getTasksByUserId(userId);
    }

    @PostMapping("/create")
    public void createTask(@RequestBody TaskDto taskDto) {
        taskService.create(taskDto);
    }

    @PutMapping("/update")
    public void updateTask(@RequestBody TaskDto taskDto) {
        taskService.update(taskDto);
    }

    @DeleteMapping("/delete-by-task-id/{taskId}")
    public void deleteByTaskId(@PathVariable("taskId") UUID taskId) {
        taskService.deleteById(taskId);
    }
}
