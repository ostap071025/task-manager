package com.task_manager.domain.Task;

public enum TaskPriority {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private final int level;

    TaskPriority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
