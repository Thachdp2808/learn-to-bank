package com.tiz.learntobank.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    public String code;
    public String message;
    public LocalDateTime timestamp =  LocalDateTime.now();
}
