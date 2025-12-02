package com.unisocial.user_service.dto;
import lombok.Data;

@Data
public class UniRequest<T> {
//    private Long userId;           
    private long requestTimestamp; 
    private T data;                
}
