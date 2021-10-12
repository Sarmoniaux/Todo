package demo.todo.controller;

import com.mysema.commons.lang.Assert;
import com.sun.xml.bind.v2.TODO;
import demo.todo.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TodosTest {

    Todos todos = new Todos();

    @Test
    void todos() {
        //Arrange
        //Act
        var result = todos.todos();
        //Assert
        assertEquals(2, result.size());
        assertEquals("Nourrir le chat", ((Todo) result.get(0)).getTitle());
        assertEquals(State.TODO, ((Todo) result.get(0)).getState());
        assertEquals("Eteindre les lumières", ((Todo) result.get(1)).getTitle());
        assertEquals(State.TODO, ((Todo) result.get(1)).getState());
    }

    @Test
    void getTodos() {
        //Arrange
        //Act
        var result = todos.getTodos();
        //Assert
        assertEquals(2, result.size());
        assertEquals("Nourrir le chat", ((Todo) result.get(0)).getTitle());
        assertEquals(State.TODO, ((Todo) result.get(0)).getState());
        assertEquals("Eteindre les lumières", ((Todo) result.get(1)).getTitle());
        assertEquals(State.TODO, ((Todo) result.get(1)).getState());
    }

    @Test
    void setTodos() {
        //Arrange
        Todo todo1 = new Todo(2,"Sauver l'univers", State.DONE);
        Todo todo2 = new Todo(3,"Appeler maman", State.IN_PROGRESS);
        ArrayList todoList = new ArrayList();
        todoList.add(todo1);
        todoList.add(todo2);
        todos.setTodos(todoList);
        //Act
        ArrayList result = todos.getTodos();
        //Assert
        assertEquals(2, result.size());
        assertEquals("Sauver l'univers", ((Todo) result.get(0)).getTitle());
        assertEquals(State.DONE, ((Todo) result.get(0)).getState());
        assertEquals("Appeler maman", ((Todo) result.get(1)).getTitle());
        assertEquals(State.IN_PROGRESS, ((Todo) result.get(1)).getState());
    }

    @Test
    void add() {
        //Arrange
        Todo todoToAdd = new Todo(2, "Sauver l'univers", State.DONE);
        //Act
        todos.add(todoToAdd);
        ArrayList result = todos.getTodos();
        //Assert
        assertEquals(3, result.size());
        assertEquals("Nourrir le chat", ((Todo) result.get(0)).getTitle());
        assertEquals(State.TODO, ((Todo) result.get(0)).getState());
        assertEquals("Eteindre les lumières", ((Todo) result.get(1)).getTitle());
        assertEquals(State.TODO, ((Todo) result.get(1)).getState());
        assertEquals("Sauver l'univers", ((Todo) result.get(2)).getTitle());
        assertEquals(State.DONE, ((Todo) result.get(2)).getState());
    }

    @Test
    void create() {
        //Act
        todos.create("Sauver l'univers");
        ArrayList result = todos.getTodos();
        //Assert
        assertEquals(3, result.size());
        assertEquals("Nourrir le chat", ((Todo) result.get(0)).getTitle());
        assertEquals(State.TODO, ((Todo) result.get(0)).getState());
        assertEquals("Eteindre les lumières", ((Todo) result.get(1)).getTitle());
        assertEquals(State.TODO, ((Todo) result.get(1)).getState());
        assertEquals("Sauver l'univers", ((Todo) result.get(2)).getTitle());
        assertEquals(State.TODO, ((Todo) result.get(2)).getState());
    }

    @Test
    void updateState() {
        //Act
        todos.updateState("0",State.IN_PROGRESS.toString());
        ArrayList result = todos.getTodos();
        //Assert
        assertEquals(2, result.size());
        assertEquals("Nourrir le chat", ((Todo) result.get(0)).getTitle());
        assertEquals(State.IN_PROGRESS, ((Todo) result.get(0)).getState());
        assertEquals("Eteindre les lumières", ((Todo) result.get(1)).getTitle());
        assertEquals(State.TODO, ((Todo) result.get(1)).getState());
    }

    @Test
    void getTodo() {
        //Act
        Todo result = todos.getTodo("1");
        //Assert
        assertEquals("Eteindre les lumières", result.getTitle());
        assertEquals(State.TODO, result.getState());
        assertEquals("C'est pas Versaille ici", result.getDescription());
    }

    @Test
    void createTodo(){
        Todo todo = new Todo(123,"test","test description");
        ResponseEntity<Map<String, Object>> response =  todos.createTodo(todo);
        Todo returnedTodo = todos.getTodo("2");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("test", returnedTodo.getTitle());
        assertEquals(State.TODO, returnedTodo.getState());
        assertEquals("test description", returnedTodo.getDescription());
    }

}