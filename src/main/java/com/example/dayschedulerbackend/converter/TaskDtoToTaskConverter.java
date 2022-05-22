package com.example.dayschedulerbackend.converter;

import com.example.dayschedulerbackend.domain.dto.TaskDto;
import com.example.dayschedulerbackend.domain.entity.Task;
import com.example.dayschedulerbackend.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TaskDtoToTaskConverter implements Converter<TaskDto, Task> {

    @Override
    public Task convert(TaskDto source) {
        return Task.builder()
                .id(source.getId())
                .title(source.getTitle())
                .startTime(source.getStartTime())
                .endTime(source.getEndTime())
                .isAnchor(source.isAnchor())
                .isReminder(source.isReminder())
                .tag(source.getTag())
                .description(source.getDescription())
                .build();
    }
}
