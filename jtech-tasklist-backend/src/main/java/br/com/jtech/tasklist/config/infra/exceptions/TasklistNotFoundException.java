package br.com.jtech.tasklist.config.infra.exceptions;

public class TasklistNotFoundException extends RuntimeException {
    public TasklistNotFoundException(String id) {
        super("Tasklist not found with id: " + id);
    }
}

