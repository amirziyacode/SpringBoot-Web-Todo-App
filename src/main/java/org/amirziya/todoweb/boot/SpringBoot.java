package org.amirziya.todoweb.boot;

import lombok.RequiredArgsConstructor;
import org.amirziya.todoweb.model.Todo;
import org.amirziya.todoweb.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SpringBoot implements CommandLineRunner {

    @Autowired
    TodoRepo todoRepo;

    @Transactional
    @Override
    public void run(String... args) {
        loadTodos();
    }
    public void loadTodos() {
        if(todoRepo.count() == 0) {
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


            todoRepo.save(todo);
            todoRepo.save(todo1);
            todoRepo.save(todo2);
        }
    }


}
