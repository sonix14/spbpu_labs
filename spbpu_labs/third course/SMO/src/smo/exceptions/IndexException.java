package com.example.smo.exceptions;

public class IndexException extends Exception {
    public IndexException() {
        super("Index is out of bounds!");
    }
}
