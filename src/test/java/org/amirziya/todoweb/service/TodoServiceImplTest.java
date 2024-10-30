package org.amirziya.todoweb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.amirziya.todoweb.controller.TodoController;
import org.amirziya.todoweb.model.Todo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

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

    @Test
    void crete_Todo() throws Exception {
        Todo todo = todoServiceImp.getAll().get(0);
        todo.setId(null);
        given(todoService.save(any(Todo.class))).willReturn(todoServiceImp.getAll().get(1));
        mockMvc.perform(post(TodoController.TODO_PATCH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todo)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
    @Test
    void delete_Todo() throws Exception{
        Todo delTodo = todoServiceImp.getAll().get(0);
        mockMvc.perform(delete(TodoController.TODO_ID,delTodo.getId())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        verify(todoService).delete(uuidArgumentCaptor.capture());
        Assertions.assertThat(delTodo.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }
}