package demo.todo.controller;

import com.mysema.commons.lang.Assert;
import com.sun.xml.bind.v2.TODO;
import demo.todo.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        Todo todo1 = new Todo("Sauver l'univers", State.DONE);
        Todo todo2 = new Todo("Appeler maman", State.IN_PROGRESS);
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
        Todo todoToAdd = new Todo("Sauver l'univers", State.DONE);
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

}