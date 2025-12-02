package com.unisocial.user_service.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String email;
	
	 @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	 private LocalDateTime created;

	 @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	 private LocalDateTime modified;
	
	private String password;

}
