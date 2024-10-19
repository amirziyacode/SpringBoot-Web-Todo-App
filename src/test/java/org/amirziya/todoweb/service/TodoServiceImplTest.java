package org.amirziya.todoweb.service;

import org.amirziya.todoweb.controller.TodoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
    void get_All_And_Count() throws Exception {
        given(todoService.getAll()).willReturn(todoServiceImp.getAll());
        mockMvc.perform(get(TodoController.TODO_PATCH).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()",is(3))) // length for json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


    }

}