package demo.todo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import demo.todo.entity.JsonViews;
import demo.todo.entity.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "*")
public class Todos {
    private int id = 0;

    @JsonView(JsonViews.Common.class)
    private ArrayList<Todo> todos = new ArrayList();

    @GetMapping({"","/"})
    public ArrayList todos(){
        return todos;
    }

    @PutMapping({"/{id}/{state}"})
    public Todo updateState(@PathVariable(value = "id") String id,@PathVariable(value = "state") String state){
        int idTodo = Integer.valueOf(id);
        for(int i = 0; todos.size() > i; i++){
            if(todos.get(i).getId() == idTodo){
                todos.get(i).setState(State.valueOf(state));
                return todos.get(i);
            }
        }
        return null;
    }

    public ArrayList getTodos() {
        return todos;
    }

    public void setTodos(ArrayList todos) {
        this.todos = todos;
    }

    public Todos(){
        this.create("Nourrir le chat");
        this.create("Eteindre les lumières");
    }

    public void add(Todo todo){
        todos.add(todo);
    }

    public void create(String title){
        todos.add(new Todo(id,title));
        id++;
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
