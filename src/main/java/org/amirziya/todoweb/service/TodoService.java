package org.amirziya.todoweb.service;


import org.amirziya.todoweb.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getAll();
    Optional<Todo> getById(int id);
    Todo save(Todo todo);
    void update(int id, Todo upTodo);
    void delete(int id);
}
