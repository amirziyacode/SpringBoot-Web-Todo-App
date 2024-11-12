package org.amirziya.todoweb.controller;

import jakarta.transaction.Transactional;
import org.amirziya.todoweb.model.Todo;
import org.amirziya.todoweb.repo.TodoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TodoControllerIT {
    @Autowired
    TodoController todoController;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    TodoRepo todoRepo;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void get_all_todos(){
        List<Todo> all = todoRepo.findAll();
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    void update_Todo(){
        Todo todo = todoRepo.findAll().get(0);
        todo.setTitle("Updated Title");
        todo.setDescription("Updated Description");

        ResponseEntity<Todo> upTodo = todoController.updateTodo(todo.getId(),todo);

        assertThat(upTodo.getStatusCode()).isEqualTo(HttpStatusCode.valueOf( 204));
        Todo byId = todoRepo.findById(todo.getId()).get();
        assertThat(byId.getTitle()).isEqualTo(todo.getTitle());
        assertThat(byId.getDescription()).isEqualTo(todo.getDescription());
    }

    @Test
    @Transactional
    @Rollback
    void save_Todo_in_db(){
        Todo newTodo = Todo.builder()
                .title("Test")
                .description("This is Todo !!")
                .isDO(false)
                .build();

        ResponseEntity<Todo> todo = todoController.createTodo(newTodo);

        assertThat(todo.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(todo.getHeaders().getLocation()).isNotNull();
    }

    @Test
    void get_Todo_by_Id(){
        Todo first = todoRepo.findAll().get(0);
        ResponseEntity<Optional<Todo>> todoById = todoController.getTodoById(first.getId());

        assertThat(todoById).isNotNull();
    }

    @Rollback
    @Transactional
    @Test
    void empty_Data(){
        todoRepo.deleteAll();
        ResponseEntity<List<Todo>> listResponseEntity = todoController.listTodo();
        assertThat(Objects.requireNonNull(listResponseEntity.getBody()).size()).isEqualTo(0);
    }
}