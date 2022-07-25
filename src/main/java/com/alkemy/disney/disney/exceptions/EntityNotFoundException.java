package com.alkemy.disney.disney.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String error) { super(error); }
}
