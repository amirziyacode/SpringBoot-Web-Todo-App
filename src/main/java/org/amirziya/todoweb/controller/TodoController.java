package org.amirziya.todoweb.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.amirziya.todoweb.model.Todo;
import org.amirziya.todoweb.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TodoController {

    public final TodoService todoService;


    @GetMapping("/api/v1/todos")
    public ResponseEntity<List<Todo>> listTodo(){
        return new ResponseEntity<>(todoService.getAll(), HttpStatus.FOUND);
    }
}
