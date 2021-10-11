package demo.todo.controller;

public enum State {
    TODO("To do"),
    IN_PROGRESS("In progess"),
    DONE("Done");

    private String value;

    State(String value) {
        this.value = value;
    }

}
