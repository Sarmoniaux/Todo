package demo.todo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import demo.todo.entity.JsonViews;
import demo.todo.entity.Todo;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "*")
public class Todos {

    @GetMapping({"","/"})
    public ArrayList todos(){
        return todos;
    }

    public ArrayList getTodos() {
        return todos;
    }

    public void setTodos(ArrayList todos) {
        this.todos = todos;
    }

    @JsonView(JsonViews.Common.class)
    private ArrayList todos = new ArrayList();

    public Todos(){
        this.create("Nourrir le chat");
        this.create("Eteindre les lumières");
    }

    public void add(Todo todo){
        todos.add(todo);
    }

    public void create(String title){
        todos.add(new Todo(title));
    }

    @Override
    public String toString() {
        return "demo.todo.controller.Todos{" +
                "todos=" + todos +
                '}';
    }

    public static void main (String[] args){
        System.out.println("Hello World");
        Todos todos = new Todos();
        todos.create("Nourrir le chat");
        todos.create("Eteindre les lumières");
        System.out.println(todos);
    }
}
