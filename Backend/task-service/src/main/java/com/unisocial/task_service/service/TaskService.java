package com.unisocial.task_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisocial.task_service.dto.TaskRequest;
import com.unisocial.task_service.entity.Task;
import com.unisocial.task_service.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repo;

    public Task create(Long userId, TaskRequest req) {
        Task t = new Task();
        t.setTitle(req.getTitle());
        t.setDescription(req.getDescription());
        t.setUserId(userId);
        return repo.save(t);
    }

    public Task get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Task> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    public Task update(Long id, Long userId, TaskRequest req) {
        Task t = repo.findById(id).orElse(null);
        if (t == null || !t.getUserId().equals(userId)) return null;

        t.setTitle(req.getTitle());
        t.setDescription(req.getDescription());

        return repo.save(t);
    }

    public boolean delete(Long id) {
        Task t = repo.findById(id).orElse(null);
        if (t == null) return false;

        repo.delete(t);
        return true;
    }
}
