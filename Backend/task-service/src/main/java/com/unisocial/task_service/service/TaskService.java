package com.unisocial.task_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unisocial.task_service.dto.TaskRequest;
import com.unisocial.task_service.dto.UniRequest;
import com.unisocial.task_service.entity.Task;
import com.unisocial.task_service.repository.TaskRepository;

import lombok.Data;

@Data
@Service
public class TaskService {
	
	@Autowired
    private TaskRepository repo;

	public Task create(UniRequest<TaskRequest> req) {
        Task t = new Task();
        t.setTitle(req.getData().getTitle());
        t.setDescription(req.getData().getDescription());
        t.setUserId(req.getUserId());
        return repo.save(t);
    }

    public Task get(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Task> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    public Task update(Long id, UniRequest<TaskRequest> req) {
        Task t = repo.findById(id).orElse(null);
        if (t == null) return null;

        t.setTitle(req.getData().getTitle());
        t.setDescription(req.getData().getDescription());
        t.setUserId(req.getUserId());

        return repo.save(t);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
