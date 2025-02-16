package org.amirziya.todoweb.repo;


import org.amirziya.todoweb.model.Category;
import org.amirziya.todoweb.model.Priority;
import org.amirziya.todoweb.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoRepoTest {
    @Autowired
    private  TodoRepo todoRepo;

    @BeforeEach
    void setUp() {
        Todo mockTodo = Todo.builder()
                .id(4)
                .category(Category.PERSONAL)
                .Completed(false)
                .priority(Priority.MEDIUM)
                .description("Play FootBall !")
                .build();
        Todo mockTodo1 = Todo.builder()
                .id(2)
                .category(Category.PERSONAL)
                .Completed(true)
                .priority(Priority.MEDIUM)
                .description("Play FootBall !")
                .build();
        todoRepo.save(mockTodo);
        todoRepo.save(mockTodo1);
    }

    @Test
    @Transactional
    void findById() {
        assertThat(todoRepo.findById(4).get().getDescription()).isEqualTo("Play FootBall !");
    }

    @Test
    @Transactional
    @Rollback
    void findCompleteTasks() {
        assertThat(todoRepo.findCompleteTasks().size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback
    void findNotCompleteTasks() {
        assertThat(todoRepo.findNotCompleteTasks().size()).isEqualTo(1);
    }
}