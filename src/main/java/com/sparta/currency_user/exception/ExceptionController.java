package com.sparta.currency_user.exception;

import com.sparta.currency_user.enumclass.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    // 사용자 지정 예외처리 기능
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> customExceptionHandler(CustomException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("errorCode", ex.getErrorCode().getErrorCode());
        response.put("message", ex.getErrorCode().getMessage());

        return ResponseEntity.status(ex.getErrorCode().getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {

        Map<String, String> response = new HashMap<>();
        response.put("errorCode", ErrorCode.INVALID_PARAMETER.getErrorCode());
        response.put("message", ErrorCode.INVALID_PARAMETER.getMessage() + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> exceptionHandler(Exception ex) {

        Map<String, String> response = new HashMap<>();
        response.put("errorCode", ErrorCode.INTERNAL_SERVER_ERROR.getErrorCode());
        response.put("message", ErrorCode.INTERNAL_SERVER_ERROR.getMessage());

        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus()).body(response);
    }
}
