package org.amirziya.todoweb.controller;


import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.amirziya.todoweb.model.Todo;
import org.amirziya.todoweb.service.TodoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TodoController {
    public static final String TODO_PATCH = "/api/v1/todos";
    public static final String TODO_ID = TODO_PATCH + "/" + "{todoId}";

    public final TodoService todoService;


    @GetMapping(value = TODO_PATCH)
    public ResponseEntity<List<Todo>> listTodo(){
        return new ResponseEntity<>(todoService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = TODO_ID)
    public ResponseEntity<Optional<Todo>> getTodoById(@PathVariable UUID todoId){
      return new ResponseEntity<>(todoService.getById(todoId),HttpStatus.FOUND);
    }

    @PutMapping(TODO_PATCH)
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        Todo saveTodo = todoService.save(todo);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location",TODO_PATCH +"/" + saveTodo.getId());
        return new ResponseEntity<>(httpHeaders,HttpStatus.CREATED);
    }
}
