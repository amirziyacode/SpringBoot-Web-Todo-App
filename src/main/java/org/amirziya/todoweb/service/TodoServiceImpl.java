package org.amirziya.todoweb.service;
import org.amirziya.todoweb.model.Category;
import org.amirziya.todoweb.model.Priority;
import org.amirziya.todoweb.model.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class TodoServiceImpl implements TodoService {

    private final Map<Integer,Todo> todoMap;

    // Mock Tasks
    public  TodoServiceImpl(){
        todoMap =  new HashMap<>();
        Todo todo = Todo.builder()
                .category(Category.PERSONAL)
                .description("Play FootBall !")
                .priority(Priority.HIGH)
                .Completed(false)
                .CreateDate(LocalDate.now())
                .build();
        Todo todo1 = Todo.builder()
                .description("Learn Math !")
                .category(Category.EDUCATION)
                .priority(Priority.MEDIUM)
                .Completed(false)
                .CreateDate(LocalDate.now())
                .build();

        Todo todo2 = Todo.builder()
                .category(Category.PERSONAL)
                .description("Do Todos !")
                .priority(Priority.HIGH)
                .Completed(false)
                .CreateDate(LocalDate.now())
                .build();

        todoMap.put(1,todo);
        todoMap.put(2,todo1);
        todoMap.put(3,todo2);
    }
    @Override
    public List<Todo> getAll() {
        return new ArrayList<>(todoMap.values());
    }

    @Override
    public List<Todo> getCompleteTask() {
        return List.of();
    }

    @Override
    public List<Todo> getNotCompleteTask() {
        return List.of();
    }

    @Override
    public Optional<Todo> getById(int id) {
        return Optional.of(todoMap.get(id));
    }

    @Override
    public Todo save(Todo todo) {
        AtomicReference<Todo> newTodo = new AtomicReference<>(Todo.builder()
                .priority(todo.getPriority())
                .description(todo.getDescription())
                .Completed(todo.isCompleted())
                .build());

//        todoMap.put(todoMap.size() +1,newTodo);
        return newTodo.get();
    }

    @Override
    public Todo setIsDo(int id, Todo isDo) {
        return isDo;
    }

    @Override
    public void update(int id, Todo todo) {
    }

    @Override
    public void delete(int id) {
    }
}
