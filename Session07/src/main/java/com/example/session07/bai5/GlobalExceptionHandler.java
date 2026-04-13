package com.example.session07.bai5;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public String handleFileTooLarge(IllegalStateException ex) {
        if (ex.getMessage() != null && ex.getMessage().contains("exceeds maximum size")) {
            return "Lỗi: File quá lớn (tối đa 10MB)";
        }
        return "Lỗi hệ thống";
    }
}
