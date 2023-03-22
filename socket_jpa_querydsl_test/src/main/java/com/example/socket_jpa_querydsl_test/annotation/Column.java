package com.example.socket_jpa_querydsl_test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target(value={METHOD, FIELD})
@Retention(value= RUNTIME)
public @interface Column {
    boolean unique = false;
}
