package demo.todo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import demo.todo.controller.State;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
    @JsonView(JsonViews.Unique.class)
    @Column(name = "description")
    private String description;


    public Todo(){
        this.id = -1;
        this.title = "";
        this.state = State.TODO;
        this.description = "";
    }

    public Todo(int id, String title){
        this.id = id;
        this.title = title;
        this.state = State.TODO;
        this.description = "";
    }

    public Todo(int id, String title, State state){
        this.id = id;
        this.title = title;
        this.state = state;
        this.description = "";
    }

    public Todo(int id, String title, State state, String description){
        this.id = id;
        this.title = title;
        this.state = state;
        this.description = description;
    }
    public Todo(int id, String title, String description){
        this.id = id;
        this.title = title;
        this.state = State.TODO;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "demo.todo.entity.Todo{" +
                "title='" + title + '\'' +
                ", state=" + state +
                '}';
    }
}
