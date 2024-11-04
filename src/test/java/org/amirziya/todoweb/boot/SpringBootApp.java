package org.amirziya.todoweb.boot;


import org.amirziya.todoweb.repo.TodoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SpringBootApp {

    @Autowired
    TodoRepo todoRepo;

    @Test
    public void countTodos(){
        assertThat(todoRepo.count()).isEqualTo(3);
    }


}