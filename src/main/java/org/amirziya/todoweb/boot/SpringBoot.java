package org.amirziya.todoweb.boot;

import lombok.RequiredArgsConstructor;
import org.amirziya.todoweb.model.Todo;
import org.amirziya.todoweb.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SpringBoot implements CommandLineRunner {
    private final TodoRepository todoRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
//        loadTodos();
    }

    public void loadTodos() {
        Todo todo = Todo.builder()
                .description("This is a todo")
                .title("Personal")
                .build();
        todoRepository.save(todo);
    }


}
