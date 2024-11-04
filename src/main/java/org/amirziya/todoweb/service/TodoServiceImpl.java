package org.amirziya.todoweb.service;

import org.amirziya.todoweb.model.Todo;
import org.amirziya.todoweb.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepo todoRepo;


    @Override
    public List<Todo> getAll() {

        return new ArrayList<> (todoRepo.findAll());
    }

    @Override
    public Optional<Todo> getById(int id) {
        return Optional.ofNullable(todoRepo.findById(id));
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
    public void update(int id, Todo todo) {
        Todo updateTodo = todoRepo.findById(id);
        updateTodo.setDescription(todo.getDescription());
        updateTodo.setTitle(todo.getTitle());
        updateTodo.setDO(todo.isDO());
        todoRepo.save(updateTodo);
    }


    @Override
    public void delete(int id) {
        todoRepo.deleteById(id);
    }
}
