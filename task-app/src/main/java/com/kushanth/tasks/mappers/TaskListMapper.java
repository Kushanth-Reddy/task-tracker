package com.kushanth.tasks.mappers;

import com.kushanth.tasks.domain.dto.TaskListDto;
import com.kushanth.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
