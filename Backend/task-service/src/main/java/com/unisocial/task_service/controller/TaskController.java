package com.unisocial.task_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.unisocial.task_service.dto.TaskRequest;
import com.unisocial.task_service.dto.UniRequest;
import com.unisocial.task_service.dto.UniResponse;
import com.unisocial.task_service.entity.Task;
import com.unisocial.task_service.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    private Long getLoggedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Long.parseLong(auth.getName());
    }

    /** CREATE */
    @PostMapping("/create")
    public ResponseEntity<UniResponse<Task>> create(@RequestBody UniRequest<TaskRequest> request) {
        try {
            Long userId = getLoggedUserId();
            Task savedTask = service.create(userId, request.getData());
            return ResponseEntity.ok(UniResponse.success(savedTask, "Task created successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(UniResponse.error("CREATE_FAILED", e.getMessage()));
        }
    }

    /** READ */
    @GetMapping("/{id}")
    public ResponseEntity<UniResponse<Task>> get(@PathVariable Long id) {
        Task t = service.get(id);
        if (t == null) {
            return ResponseEntity.badRequest()
                    .body(UniResponse.error("NOT_FOUND", "Task not found"));
        }
        return ResponseEntity.ok(UniResponse.success(t, "Task fetched successfully"));
    }

    /** UPDATE */
    @PutMapping("/update/{id}")
    public ResponseEntity<UniResponse<Task>> update(
            @PathVariable Long id,
            @RequestBody UniRequest<TaskRequest> request) {

        Long userId = getLoggedUserId();
        Task updated = service.update(id, userId, request.getData());

        if (updated == null) {
            return ResponseEntity.badRequest()
                    .body(UniResponse.error("UPDATE_FAILED", "Task not found or not owned by user"));
        }

        return ResponseEntity.ok(UniResponse.success(updated, "Task updated successfully"));
    }

    /** DELETE */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UniResponse<Void>> delete(@PathVariable Long id) {

        boolean deleted = service.delete(id);
        if (!deleted) {
            return ResponseEntity.badRequest()
                    .body(UniResponse.error("DELETE_FAILED", "Task not found or not owned by user"));
        }
        return ResponseEntity.ok(UniResponse.success(null, "Task deleted successfully"));
    }

    /** USER’S TASKS */
    @GetMapping("/my")
    public ResponseEntity<UniResponse<List<Task>>> getMyTasks() {
        Long userId = getLoggedUserId();
        List<Task> tasks = service.getByUser(userId);
        return ResponseEntity.ok(UniResponse.success(tasks, "Fetched user tasks"));
    }
}
