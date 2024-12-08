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
        return Optional.of(todoRepo.findById(id)).orElse(null);
    }

    @Override
    public Todo save(Todo todo) {
        Todo newTodo = Todo.builder()
                .title(todo.getTitle())
                .CreateDate(LocalDate.now())
                .UpdateDate(LocalDate.now())
                .description(todo.getDescription())
                .isDO(todo.isDO())
                .build();
        todoRepo.save(newTodo);
        return newTodo;
    }

    @Override
    public void setIsDo(int id, Todo todo) {
        todoRepo.findById(id).ifPresent(foundTodo -> {
            foundTodo.setDO(todo.isDO());
            todoRepo.save(foundTodo);
        });
    }

    @Override
    public void update(int id, Todo todo) {
        Todo updTodo = todoRepo.getReferenceById(id);
        updTodo.setTitle(todo.getTitle());
        updTodo.setDescription(todo.getDescription());
        updTodo.setDO(todo.isDO());
        updTodo.setUpdateDate(LocalDate.now());
        todoRepo.save(updTodo);
    }

    @Override
    public void delete(int id) {
        if(todoRepo.existsById(id)) {
            todoRepo.deleteById(id);
        }
    }
}
