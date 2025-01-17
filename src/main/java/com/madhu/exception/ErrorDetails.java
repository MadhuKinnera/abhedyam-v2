package com.madhu.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDetails {
    private Integer status;
    private String message;
    private LocalDateTime timestamp;

}
