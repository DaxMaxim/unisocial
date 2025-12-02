package com.unisocial.user_service.dto;

import lombok.Data;

@Data
public class UniResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String errorCode; // null in case of success
    private long responseTimestamp = System.currentTimeMillis();

    public static <T> UniResponse<T> success(T data, String message) {
        UniResponse<T> res = new UniResponse<>();
        res.success = true;
        res.data = data;
        res.message = message;
        return res;
    }

    public static <T> UniResponse<T> error(String errorCode, String message) {
        UniResponse<T> res = new UniResponse<>();
        res.success = false;
        res.errorCode = errorCode;
        res.message = message;
        return res;
    }
}
