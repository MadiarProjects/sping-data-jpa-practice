package org.example.springdatajpapractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springdatajpapractice.model.Task;
import org.example.springdatajpapractice.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    //  GET   /tasks?assignedTo=1,2&status=
    @GetMapping
    public List<Task> getTasks(
            @RequestParam(required = false) List<Integer> assignedTo,
            @RequestParam(required = false) List<Task.Status> status,
            @RequestParam(required = false) List<Task.Priority> priority
    ) {
        return taskRepository.findByMultipleCondition(assignedTo, status, priority);
    }
}