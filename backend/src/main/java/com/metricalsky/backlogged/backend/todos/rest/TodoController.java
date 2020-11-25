package com.metricalsky.backlogged.backend.todos.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.metricalsky.backlogged.backend.todos.model.Todo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private static final List<Todo> todos = new ArrayList<>();

    @GetMapping
    public List<Todo> list() {
        return todos;
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        todo.setKey(UUID.randomUUID().toString());
        todos.add(todo);
        return todo;
    }
}
