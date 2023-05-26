package com.example.dayschedulerbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDto {
    private UUID id;
    private UUID userId;
    private String title;
    @NonNull
    private long startTime;
    @Nullable
    private Long endTime;
    private boolean isReminder;
    private boolean isAnchor;
    private String tag;
    private String description;
    private List<UserDto> userDtoList;
}
