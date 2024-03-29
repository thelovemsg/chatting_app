package com.example.socket_jpa_querydsl_test.config.exception.config;

import com.example.socket_jpa_querydsl_test.api.Message;
import com.example.socket_jpa_querydsl_test.api.StatusEnum;
import com.example.socket_jpa_querydsl_test.config.exception.AlreadyFriendException;
import com.example.socket_jpa_querydsl_test.config.exception.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.example.socket_jpa_querydsl_test")
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Message> errorHandler(IllegalArgumentException e) {
        log.error(e.getMessage());
        Message message;
        message = new Message(StatusEnum.BAD_REQUEST, e.getMessage(), null);
        return new ResponseEntity(message, HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error(e.getMessage());
        Message message = new Message(StatusEnum.UNAUTHORIZED, e.getMessage(), null);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = AlreadyFriendException.class)
    public ResponseEntity<Object> handleAlreadyFriendException(AlreadyFriendException e) {
        log.error(e.getMessage());
        Message message = new Message(StatusEnum.BAD_REQUEST, e.getMessage(), e.getData());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
