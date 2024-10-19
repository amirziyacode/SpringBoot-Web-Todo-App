package org.amirziya.todoweb.service;

import org.amirziya.todoweb.model.Todo;
import org.springframework.stereotype.Service;

import java.util.*;

import static jdk.internal.classfile.impl.DirectCodeBuilder.build;

@Service
public class TodoServiceImpl implements TodoService {

    private final Map<UUID, Todo> todoMap;

    public TodoServiceImpl(){
        this.todoMap = new HashMap<>();
        Todo todo = Todo.builder()
                .id(UUID.randomUUID())
                .title("Personal")
                .description("This is Todo !")
                .build();

        Todo todo1 = Todo.builder()
                .id(UUID.randomUUID())
                .title("Sport")
                .description("Play Tennis")
                .build();

        Todo todo2 = Todo.builder()
                .id(UUID.randomUUID())
                .title("Study")
                .description("Learn Math !")
                .build();

        todoMap.put(todo.getId(),todo);
        todoMap.put(todo1.getId(),todo1);
        todoMap.put(todo2.getId(),todo2);
    }

    @Override
    public List<Todo> getAll() {
        return new ArrayList<>(todoMap.values());
    }

    @Override
    public Optional<Todo> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Todo todo) {
        Todo newTodo = Todo.builder()
                .id(UUID.randomUUID())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .build();
        todoMap.put(todo.getId(),newTodo);
    }

    @Override
    public Void update(Todo todo, Long id) {
        return null;
    }

    @Override
    public void delete(Todo todo) {

    }
}
