package com.unisocial.task_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unisocial.task_service.dto.TaskRequest;
import com.unisocial.task_service.dto.UniRequest;
import com.unisocial.task_service.entity.Task;
import com.unisocial.task_service.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
    private TaskService service;

    // CREATE
	@PostMapping("/create")
	public Task create(@RequestBody UniRequest<TaskRequest> request) {
	    return service.create(request);
	}

    // READ
    @GetMapping("/{id}")
    public Task get(@PathVariable Long id) {
        return service.get(id);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public Task update(@PathVariable Long id, @RequestBody UniRequest<TaskRequest> request) {
        return service.update(id, request);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
