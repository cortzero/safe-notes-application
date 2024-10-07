package com.cortzero.safenotes.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String category) {
        super("The category " + category + " does not exist.");
    }
}
