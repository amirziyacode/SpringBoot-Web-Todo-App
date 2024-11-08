package org.amirziya.todoweb.service;


import org.amirziya.todoweb.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getAll();
    Optional<Todo> getById(int id);
    Todo save(Todo todo);
    Boolean setIsDo(int id, Todo todo);
    void update(int id, Todo todo);
    void delete(int id);
}
