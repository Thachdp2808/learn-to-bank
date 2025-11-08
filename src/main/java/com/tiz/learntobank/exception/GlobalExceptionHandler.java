package com.tiz.learntobank.exception;

import com.tiz.learntobank.untils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<Object> handleCommonException(CommonException ex) {
        LinkedHashMap<String, Object> errors = new LinkedHashMap<>();

        switch (ex.getCommonException()) {
            case SYSTEM_ERROR -> errors.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            case JSON_ERROR, DATA_TYPE_ERROR, MANDATORY_FIELD_ERROR, CLIENT_ERROR, TRADER_NOT_EXIST,
                 RECEPTION_NOT_EXIST, WORK_ORDER_ID_NOT_EXIST, INVALID_URL, METHOD_NOT_ALLOW
                    -> errors.put("status", HttpStatus.BAD_REQUEST.value());
            case AUTHENTICATION_ERROR -> errors.put("status", HttpStatus.UNAUTHORIZED.value());
        }
        errors.put("message", "error");

        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("errorCode", ex.getCommonException().getCode());
        data.put("errorDescription", String.valueOf(ex.getCommonException().getErrorDescription()));

        errors.put("data", data);
        return new ResponseEntity<>(errors, HttpStatusCode.valueOf(HttpStatus.valueOf((Integer) errors.get("status")).value()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleRuntime(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("Runtime error: " + e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorized(RuntimeException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 401);
        body.put("error", "Unauthorized");
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }


}
