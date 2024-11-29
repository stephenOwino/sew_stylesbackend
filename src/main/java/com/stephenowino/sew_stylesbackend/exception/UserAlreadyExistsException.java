package com.stephenowino.sew_stylesbackend.exception;

public class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
                super(message);
        }
}