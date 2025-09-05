package br.com.jtech.tasklist.application.core.domains;

public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED;

    public static TaskStatus fromString(String value) {
        return value == null ? null : TaskStatus.valueOf(value.toUpperCase());
    }
}
