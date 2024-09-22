package com.cortzero.safenotes.exceptions;

public class StatusNotFoundException extends RuntimeException {

    public StatusNotFoundException(String status) {
        super("The status " + status + " does not exist.");
    }
}
