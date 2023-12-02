package com.example.smo.exceptions;

public class OverflowException extends Exception {
    public OverflowException() {
        super("The buffer is full!");
    }
}
