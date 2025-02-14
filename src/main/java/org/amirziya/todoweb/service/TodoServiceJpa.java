package org.amirziya.todoweb.service;

import lombok.RequiredArgsConstructor;
import org.amirziya.todoweb.model.Todo;
import org.amirziya.todoweb.repo.TodoRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
public class TodoServiceJpa implements TodoService {
    public final TodoRepo todoRepo;

    @Override
    public List<Todo> getAll() {
        return todoRepo.findAll();
    }

    @Override
    public Optional<Todo> getById(int id) {
        if(id<0 || todoRepo.findById(id).isEmpty()){
            throw  new IllegalArgumentException("Invalid id");
        }
        return Optional.of(todoRepo.findById(id)).orElse(null);
    }

    @Override
    public Todo save(Todo todo) {
        Todo newTodo = Todo.builder()
                .CreateDate(LocalDate.now())
                .UpdateDate(LocalDate.now())
                .description(todo.getDescription())
                .Completed(todo.isCompleted())
                .build();
        todoRepo.save(newTodo);
        return newTodo;
    }

    @Override
    public Todo setIsDo(int id, Todo todo) {
        Optional<Todo> byId = todoRepo.findById(id);
        if(byId.isPresent()){
            byId.get().setCompleted(todo.isCompleted());
            return byId.get();
        }
        else {
            throw  new IllegalArgumentException("Invalid id");
        }
    }

    @Override
    public void update(int id, Todo todo) {
        Optional<Todo> updTodo = todoRepo.findById(id);
        if(updTodo.isPresent()) {
            updTodo.get().setDescription(todo.getDescription());
            updTodo.get().setCompleted(todo.isCompleted());
            updTodo.get().setUpdateDate(LocalDate.now());
            todoRepo.save(updTodo.get());
        }else {
            throw  new IllegalArgumentException("Invalid id");
        }
    }

    @Override
    public void delete(int id) {
        if(todoRepo.existsById(id)) {
            todoRepo.deleteById(id);
        }else {
            throw  new IllegalArgumentException("Invalid id");
        }
    }
}
