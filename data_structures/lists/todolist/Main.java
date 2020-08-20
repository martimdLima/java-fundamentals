package com.martimlima.javaprojects.data_structures.lists.todolist;

public class Main {

    public static void main(String[] args) {

        TodoList todoList = new TodoList();

        todoList.add(Importance.LOW, 3, "take care of the plants");
        todoList.add(Importance.MEDIUM, 2, "Make dinner");
        todoList.add(Importance.MEDIUM, 2, "Prepare the info for the meeting tomorrow");
        todoList.add(Importance.HIGH, 1, "Feed the pets");
        todoList.add(Importance.HIGH, 1, "Do the taxes");

        while(!todoList.isEmpty()) {
            System.out.println(todoList.remove());
        }

    }

}
