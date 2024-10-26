package org.amirziya.todoweb.service;


import org.amirziya.todoweb.model.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoService {
    List<Todo> getAll();
    Optional<Todo> getById(UUID id);
    Todo save(Todo todo);
    Void update(Todo todo, UUID id);
    void delete(UUID id);
}
