package com.kushanth.tasks.domain.dto;

import com.kushanth.tasks.domain.entities.TaskPriority;
import com.kushanth.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        TaskPriority priority
) {

}
