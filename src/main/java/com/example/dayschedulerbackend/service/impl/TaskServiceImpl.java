package com.example.dayschedulerbackend.service.impl;

import com.example.dayschedulerbackend.domain.dto.TaskDto;
import com.example.dayschedulerbackend.domain.dto.UserDto;
import com.example.dayschedulerbackend.domain.entity.Task;
import com.example.dayschedulerbackend.domain.entity.User;
import com.example.dayschedulerbackend.repository.TaskRepository;
import com.example.dayschedulerbackend.service.TaskService;
import com.example.dayschedulerbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ConversionService conversionService;

    @Override
    public TaskDto getById(UUID taskId) {
        if (taskId != null) {
            var task = taskRepository.findById(taskId).orElse(null);

            if (task != null) {
                return conversionService.convert(task, TaskDto.class);
            }
        }

        return null;
    }

    @Override
    public List<TaskDto> getTasksByUserId(UUID userId) {
        var user = userService.getEntityById(userId);

        if (user != null) {
            return user.getCreatedByUserTasks().stream()
                    .map(task -> {
                        var taskDto = conversionService.convert(task, TaskDto.class);
                        var users = task.getUsers().stream()
                                .map(it -> conversionService.convert(it, UserDto.class))
                                .collect(Collectors.toList());
                        taskDto.setUserDtoList(users);
                        return taskDto;
                    })
                    .collect(Collectors.toList());
        }

        return List.of();
    }

    @Override
    public void create(TaskDto taskDto) {
        var res = getById(taskDto.getId());
        if (Objects.nonNull(res))
            update(taskDto);

        if (taskDto.getUserId() != null) {
            var taskEntity = conversionService.convert(taskDto, Task.class);
            var user = userService.getEntityById(taskDto.getUserId());

            if (user != null && taskDto.getUserDtoList() != null) {
                var assignedUsers = taskDto.getUserDtoList().stream()
                        .map(userDto -> userService.getEntityById(userDto.getId()))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());

                taskEntity.setUsers(assignedUsers);
                taskEntity.setUser(user);
                taskRepository.save(taskEntity);
            }
        }
    }

    @Override
    public void update(TaskDto taskDto) {
        if (taskDto.getId() != null) {
            taskRepository.findById(taskDto.getId())
                    .ifPresent(toUpdate -> {
                        var updatedTask = conversionService.convert(taskDto, Task.class);
                        var user = userService.getEntityById(taskDto.getUserId());

                        if (user != null && taskDto.getUserDtoList() != null) {
                            var assignedUsers = taskDto.getUserDtoList().stream()
                                    .map(userDto -> userService.getEntityById(userDto.getId()))
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toSet());

                            toUpdate.setTitle(updatedTask.getTitle());
                            toUpdate.setStartTime(updatedTask.getStartTime());
                            toUpdate.setEndTime(updatedTask.getEndTime());
                            toUpdate.setAnchor(updatedTask.isAnchor());
                            toUpdate.setReminder(updatedTask.isReminder());
                            toUpdate.setTag(updatedTask.getTag());
                            toUpdate.setDescription(updatedTask.getDescription());
                            toUpdate.setUsers(assignedUsers);
                            toUpdate.setUser(user);
                            taskRepository.save(toUpdate);
                        }
                    });
        }
    }

    @Override
    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }
}
