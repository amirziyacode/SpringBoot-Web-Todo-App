package org.amirziya.todoweb.service;

import lombok.RequiredArgsConstructor;
import org.amirziya.todoweb.model.Todo;
import org.amirziya.todoweb.repo.TodoRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
        return Optional.of(todoRepo.findById(id)).orElse(null);
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
        Todo updTodo = todoRepo.getReferenceById(id);
        updTodo.setTitle(todo.getTitle());
        updTodo.setDescription(todo.getDescription());
        updTodo.setDO(todo.isDO());
        todoRepo.save(updTodo);
    }

    @Override
    public void delete(int id) {
        if(todoRepo.existsById(id)) {
            todoRepo.deleteById(id);
        }
    }
}
