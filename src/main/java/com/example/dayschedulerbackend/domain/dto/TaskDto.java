package com.example.dayschedulerbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private UUID id;
    private UUID userId;
    private String title;
    private long startTime;
    private long endTime;
    private boolean isReminder;
    private boolean isAnchor;
    private String tag;
    private boolean isTaskLocal;
    private String description;
}
