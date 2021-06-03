package com.example.monday;

public class Todomodel {
    private String todo;
    private int id;

    public Todomodel(String todo, int id) {
        this.todo = todo;
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    @Override
    public String toString() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
