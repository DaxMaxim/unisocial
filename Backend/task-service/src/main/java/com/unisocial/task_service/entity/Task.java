package com.unisocial.task_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
    private Long id;

    private String title;

    private String description;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created", updatable = false, insertable = false)
    private LocalDateTime created;

    @Column(name = "modified", insertable = false, updatable = false)
    private LocalDateTime modified;
	
}
