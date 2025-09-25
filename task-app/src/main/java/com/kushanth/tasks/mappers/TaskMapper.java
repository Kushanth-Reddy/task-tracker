package com.kushanth.tasks.mappers;

import com.kushanth.tasks.domain.dto.TaskDto;
import com.kushanth.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
