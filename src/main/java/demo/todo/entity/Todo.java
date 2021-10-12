package demo.todo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import demo.todo.controller.State;

import javax.persistence.*;

@Entity
public class Todo {
    @JsonView(JsonViews.Common.class)
    @Id
    private int id;
    @JsonView(JsonViews.Common.class)
    @Column(name = "title")
    private String title;
    @JsonView(JsonViews.Common.class)
    @Column(name = "state")
    private State state;


    public Todo(int id, String title){
        this.id = id;
        this.title = title;
        this.state = State.TODO;
    }

    public Todo(int id, String title, State state){
        this.id = id;
        this.title = title;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "demo.todo.entity.Todo{" +
                "title='" + title + '\'' +
                ", state=" + state +
                '}';
    }
}
