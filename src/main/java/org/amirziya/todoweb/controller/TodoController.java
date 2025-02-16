package org.amirziya.todoweb.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.amirziya.todoweb.model.Todo;
import org.amirziya.todoweb.service.TodoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // port react App !!  => http://localhost:3000
public class TodoController {
    public static final String TODO_PATCH = "/api/v1/todos";
    public static final String TODO_ID = TODO_PATCH + "/" + "{todoId}";

    public final TodoService todoService;


    @GetMapping(value = TODO_PATCH)
    public ResponseEntity<List<Todo>> listTodo(){
        return new ResponseEntity<>(todoService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = TODO_ID)
    public ResponseEntity<Optional<Todo>> getTodoById(@PathVariable("todoId") int  todoId){
      return new ResponseEntity<>(todoService.getById(todoId),HttpStatus.OK);
    }

    @PutMapping("/api/v1/todos/do/{todoId}")
    public ResponseEntity<Todo> setDo(@PathVariable("todoId")int todoId,@RequestBody Todo isDo){
        Todo todo = todoService.setIsDo(todoId, isDo);
        return  ResponseEntity.status(HttpStatus.OK).body(todo);
    }

    @PostMapping(TODO_PATCH)
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        Todo saveTodo = todoService.save(todo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location",TODO_PATCH +"/" + saveTodo.getId());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @DeleteMapping(TODO_ID)
    public ResponseEntity<Todo> deleteTodo(@PathVariable("todoId") int todoId){
        todoService.delete(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(TODO_ID)
    public ResponseEntity<Todo> updateTodo(@PathVariable("todoId") int todoId,@RequestBody Todo todo){
        todoService.update(todoId, todo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/api/v1/todos/NotCompleted")
    public ResponseEntity<List<Todo>> getNotCompletedTasks(){
        return  ResponseEntity.status(HttpStatus.OK).body(todoService.getNotCompleteTask());
    }

    @GetMapping("/api/v1/todos/Completed")
    public ResponseEntity<List<Todo>> getCompletedTasks(){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getCompleteTask());
    }

}
