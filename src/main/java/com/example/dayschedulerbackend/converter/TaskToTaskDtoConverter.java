package com.example.dayschedulerbackend.converter;

import com.example.dayschedulerbackend.domain.dto.TaskDto;
import com.example.dayschedulerbackend.domain.entity.Task;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskToTaskDtoConverter implements Converter<Task, TaskDto> {

    @Override
    public TaskDto convert(Task source) {
        return TaskDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .startTime(source.getStartTime())
                .endTime(source.getEndTime())
                .isAnchor(source.isAnchor())
                .isReminder(source.isReminder())
                .tag(source.getTag())
                .description(source.getDescription())
                .userId(source.getUser().getId())
                .build();
    }
}
