package org.amirziya.todoweb.boot;

import lombok.RequiredArgsConstructor;
import org.amirziya.todoweb.model.Category;
import org.amirziya.todoweb.model.Priority;
import org.amirziya.todoweb.model.Todo;
import org.amirziya.todoweb.repo.TodoRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class SpringBoot implements CommandLineRunner {

    private final TodoRepo todoRepo;

    @Transactional
    @Override
    public void run(String... args) {
        loadTodos();
    }
    public void loadTodos() {
        if(todoRepo.count() == 0) {
            Todo todo = Todo.builder()
                    .priority(Priority.MEDIUM)
                    .Completed(true)
                    .CreateDate(LocalDate.now())
                    .category(Category.PERSONAL)
                    .description("Play FootBall !")
                    .build();
            Todo todo1 = Todo.builder()
                    .priority(Priority.HIGH)
                    .Completed(false)
                    .CreateDate(LocalDate.now())
                    .category(Category.EDUCATION)
                    .description("Learn Math !")
                    .build();

            Todo todo2 = Todo.builder()
                    .priority(Priority.MEDIUM)
                    .Completed(false)
                    .CreateDate(LocalDate.now())
                    .category(Category.PERSONAL)
                    .description("Do Todos !")
                    .build();


            todoRepo.save(todo);
            todoRepo.save(todo1);
            todoRepo.save(todo2);
        }
    }


}
