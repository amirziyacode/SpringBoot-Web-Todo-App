package org.amirziya.todoweb.controller;

import jakarta.transaction.Transactional;
import org.amirziya.todoweb.model.Todo;
import org.amirziya.todoweb.repo.TodoRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;
import java.util.Objects;

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
        Assertions.assertThat(all.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void empty_Data(){
        todoRepo.deleteAll();
        ResponseEntity<List<Todo>> listResponseEntity = todoController.listTodo();
        Assertions.assertThat(Objects.requireNonNull(listResponseEntity.getBody()).size()).isEqualTo(0);
    }


}