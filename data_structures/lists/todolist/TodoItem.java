package com.martimlima.javaprojects.data_structures.lists.todolist;

public final class TodoItem implements Comparable<TodoItem> {

    private final int priority;
    private final String message;
    private final Importance importance;

    public TodoItem(Importance importance, int priority, String message) {
        this.message = message;
        this.priority = priority;
        this.importance = importance;
    }

    @Override
    public final int compareTo(TodoItem todoItem) {

        /*
        if (importance.compareTo(todoItem.importance) == 0) {
            return priority - todoItem.priority;
        }

        return importance.compareTo(todoItem.importance);
        */

        return importance.compareTo(todoItem.importance) == 0 ?
                priority - todoItem.priority :
                importance.compareTo(todoItem.importance);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "importance=" + importance +
                ", priority=" + priority +
                ", message='" + message + '\'' +
                '}';
    }
}