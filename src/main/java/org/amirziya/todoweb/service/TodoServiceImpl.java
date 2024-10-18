package org.amirziya.todoweb.service;

import org.amirziya.todoweb.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    @Override
    public Optional<List<Todo>> getAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Todo> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Todo todo) {

    }

    @Override
    public Void update(Todo todo, Long id) {
        return null;
    }

    @Override
    public void delete(Todo todo) {

    }
}
