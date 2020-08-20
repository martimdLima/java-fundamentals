package com.martimlima.javaprojects.data_structures.lists.todolist;

import java.util.PriorityQueue;

public class TodoList {

    private PriorityQueue<TodoItem> priorityQueue;

    public TodoList(){
        priorityQueue = new PriorityQueue<>();
    }

    public void add(Importance importance, int priority, String message) {
        priorityQueue.add(new TodoItem(importance, priority, message));
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    public TodoItem remove() {
        return priorityQueue.poll();
    }
}
