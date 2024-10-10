package com.RentBikeSystem.BikeService.Exception;

import com.RentBikeSystem.BikeService.DTO.APIDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex)
    {
        Map<String,String> Response =new HashMap<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            Response.put(fieldName, errorMessage);

        }
        APIDTO apidto=APIDTO.builder().response(Response).build();
        return ResponseEntity.badRequest().body(apidto);
    }
}
