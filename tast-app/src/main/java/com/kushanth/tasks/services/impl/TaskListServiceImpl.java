package com.kushanth.tasks.services.impl;

import com.kushanth.tasks.domain.entities.TaskList;
import com.kushanth.tasks.repositories.TaskListRepository;
import com.kushanth.tasks.services.TaskListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll() ;
    }

    @Transactional
    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null != taskList.getId()) {
            throw new IllegalArgumentException("task list already has an Id!");
        }
        if (null == taskList.getTitle() || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("task list title must be present!");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if (null == taskList.getId()) {
            throw new IllegalArgumentException("Task List must have an id!");
        }

        if (!Objects.equals(taskList.getId(), taskListId)) {
            throw new IllegalArgumentException("Not Allowed");
        }

        TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("Task List not found!"));
        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deletTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}
