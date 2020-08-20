package com.martimlima.javaprojects.data_structures.lists.todolist_nested_classes;

import java.util.PriorityQueue;

public class TodoList {

    public enum Importance {
        HIGH,
        MEDIUM,
        LOW
    }

    private PriorityQueue<TodoItem> queue = new PriorityQueue<>();

    public void add(Importance importance, int priority, String item) {
        queue.add(new TodoItem(importance, priority, item));
    }

    public TodoItem remove() {
        return queue.remove();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    static public class TodoItem implements Comparable<TodoItem> {

        private Importance importance;
        private int priority;
        private String item;

        public TodoItem(Importance importance, int priority, String item) {
            this.importance = importance;
            this.priority = priority;
            this.item = item;
        }

        @Override
        public int compareTo(TodoItem todoItem) {

            if (importance.compareTo(todoItem.importance) == 0) {
                return priority - todoItem.priority;
            }

            return importance.compareTo(todoItem.importance);

        }

        @Override
        public String toString() {
            return "TodoItem{" +
                    "importance=" + importance +
                    ", priority=" + priority +
                    ", item='" + item + '\'' +
                    '}';
        }
    }
}

