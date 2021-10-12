package demo.todo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import demo.todo.entity.JsonViews;
import demo.todo.entity.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "*")
public class Todos {
    private int id = 0;

    @JsonView(JsonViews.Common.class)
    private ArrayList<Todo> todos = new ArrayList();

    @GetMapping({"","/"})
    @JsonView(JsonViews.Common.class)
    public ArrayList todos(){
        return todos;
    }

    @GetMapping({"/{id}"})
    @JsonView(JsonViews.Unique.class)
    public Todo getTodo(@PathVariable(value = "id") String id){
        return searchTodo(id);
    }

    @PutMapping({"/{id}/{state}"})
    public Todo updateState(@PathVariable(value = "id") String id,@PathVariable(value = "state") String state){
        Todo tempTodo = searchTodo(id);
        if(tempTodo != null) tempTodo.setState(State.valueOf(state));
        return tempTodo;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createTodo(@RequestBody Todo todo) {
        if(todo.getTitle() != null){
            this.create(todo.getTitle(),todo.getDescription());
            return (new ResponseEntity<>(new HashMap<>(), HttpStatus.OK));
        }
        return (new ResponseEntity<>(new HashMap<>(), HttpStatus.BAD_REQUEST));
    }

    private Todo searchTodo(String id){
        int idTodo = Integer.valueOf(id);
        for(int i = 0; todos.size() > i; i++){
            if(todos.get(i).getId() == idTodo){
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
        this.create("Nourrir le chat", "Mettre des croquettes dans sa gamelle");
        this.create("Eteindre les lumières", "C'est pas Versaille ici");
    }

    public void add(Todo todo){
        todos.add(todo);
    }

    public void create(String title){
        todos.add(new Todo(id,title));
        id++;
    }

    public void create(String title, String description){
        todos.add(new Todo(id,title, description));
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
