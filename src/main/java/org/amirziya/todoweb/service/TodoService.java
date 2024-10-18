package org.amirziya.todoweb.service;


import org.amirziya.todoweb.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getAll();
    Optional<Todo> getById(Long id);
    void save(Todo todo);
    Void update(Todo todo, Long id);
    void delete(Todo todo);
}
