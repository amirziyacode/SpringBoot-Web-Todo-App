package org.amirziya.todoweb.service;

import org.amirziya.todoweb.model.Todo;
import org.amirziya.todoweb.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class TodoServiceImpl implements TodoService {


    TodoRepo todoRepo;


    @Override
    public List<Todo> getAll() {
        return todoRepo.findAll();
    }

    @Override
    public Optional<Todo> getById(UUID id) {
        return todoRepo.findById(id);
    }

    @Override
    public Todo save(Todo todo) {
        Todo newTodo = Todo.builder()
                .title(todo.getTitle())
                .description(todo.getDescription())
                .isDO(todo.isDO())
                .build();

        todoRepo.save(newTodo);
        return newTodo;
    }

    @Override
    public void update(UUID id, Todo todo) {
        todoRepo.findById(id).ifPresent(foundTodo -> {
            foundTodo.setDescription(todo.getDescription());
            foundTodo.setDO(todo.isDO());
            foundTodo.setTitle(todo.getTitle());
            todoRepo.save(foundTodo);
        });
    }


    @Override
    public void delete(UUID id) {
        todoRepo.deleteById(id);
    }
}
