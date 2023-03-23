package com.example.socket_jpa_querydsl_test.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class CustomResponseUtils {
    public static <T> ResponseEntity customResponse(T data){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity(data, headers, HttpStatus.OK);
    }

    /**
     *
     * @param result
     * @return message
     * @description 하나의 메시지만 표출
     */
    public static String getErrorMessage(BindingResult result) {
        FieldError fieldError = result.getFieldError();
        return fieldError.getField() + " " + fieldError.getDefaultMessage();
    }


    /**
     *
     * @param result
     * @return message
     * @description 모든 메시지를 표출
     */
    public static String getErrorMessages(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(error -> String.format("%s %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.joining(", "));
    }


}
