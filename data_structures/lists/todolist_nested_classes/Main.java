package com.martimlima.javaprojects.data_structures.lists.todolist_nested_classes;

public class Main {

    public static void main(String[] args) {

        TodoList todoList = new TodoList();

        todoList.add(TodoList.Importance.MEDIUM, 1, "Medium priority 1");
        todoList.add(TodoList.Importance.LOW, 1, "Low priority 1");
        todoList.add(TodoList.Importance.HIGH, 1, "High priority 1");
        todoList.add(TodoList.Importance.LOW, 2, "Low priority 2");
        todoList.add(TodoList.Importance.MEDIUM, 2, "Medium priority 2");
        todoList.add(TodoList.Importance.HIGH, 2, "High priority 2");

        while (!todoList.isEmpty()) {
            System.out.println(todoList.remove());
        }

        System.out.println("\nInstantiating a TodoList.TodoItem");
        System.out.println(new TodoList.TodoItem(TodoList.Importance.HIGH, 1, "Highest Priority"));
    }
}
