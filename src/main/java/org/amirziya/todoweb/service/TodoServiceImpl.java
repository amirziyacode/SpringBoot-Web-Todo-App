package org.amirziya.todoweb.service;
import org.amirziya.todoweb.model.Todo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class TodoServiceImpl implements TodoService {

    private final Map<Integer,Todo> todoMap;

    public  TodoServiceImpl(){
        todoMap =  new HashMap<>();
        Todo todo = Todo.builder()
                .title("Sport")
                .description("Play FootBall !")
                .build();
        Todo todo1 = Todo.builder()
                .title("Study")
                .description("Learn Math !")
                .build();

        Todo todo2 = Todo.builder()
                .title("Work")
                .description("Do Todos !")
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
    public Optional<Todo> getById(int id) {
        return Optional.of(todoMap.get(id));
    }

    @Override
    public Todo save(Todo todo) {
        AtomicReference<Todo> newTodo = new AtomicReference<>(Todo.builder()
                .title(todo.getTitle())
                .description(todo.getDescription())
                .isDO(todo.isDO())
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
