package org.amirziya.todoweb.service;

import org.amirziya.todoweb.controller.TodoController;
import org.amirziya.todoweb.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(TodoController.class)
class TodoServiceImplTest {

    @MockBean
    TodoService todoService;

    @Autowired
    MockMvc mockMvc;

    TodoServiceImpl todoServiceImp;


    @BeforeEach
    void setUp(){
        todoServiceImp = new TodoServiceImpl();
    }


    @Test
    void get_Todo_By_Id() throws Exception {
         Todo testodo = Todo.builder()
                 .id(UUID.randomUUID())
                 .build();
         given(todoService.getById(testodo.getId())).willReturn(Optional.of(testodo));
        mockMvc.perform(get(TodoController.TODO_ID,testodo.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    void get_All_And_Count() throws Exception {
        given(todoService.getAll()).willReturn(todoServiceImp.getAll());
        mockMvc.perform(get(TodoController.TODO_PATCH).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()",is(3))) // length for json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


    }

}