package org.amirziya.todoweb.service;


import org.amirziya.todoweb.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getAll();
    List<Todo> getCompleteTask();
    List<Todo> getNotCompleteTask();
    Optional<Todo> getById(int id);
    Todo save(Todo todo);
    Todo setIsDo(int id, Todo isDo);
    void update(int id, Todo todo);
    void delete(int id);
}
