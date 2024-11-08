package org.amirziya.todoweb.service;

import org.amirziya.todoweb.model.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TodoServiceImpJpa implements TodoService {
    @Override
    public List<Todo> getAll() {
        return List.of();
    }

    @Override
    public Optional<Todo> getById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Todo save(Todo todo) {
        return null;
    }

    @Override
    public void update(UUID id, Todo upTodo) {

    }

    @Override
    public void delete(UUID id) {

    }
}
