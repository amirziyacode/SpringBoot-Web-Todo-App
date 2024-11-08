package org.amirziya.todoweb.boot;


import org.amirziya.todoweb.repo.TodoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class SpringBootApp {

    @Autowired
    TodoRepo todoRepo;

     SpringBoot springBoot;

     @BeforeEach
     void setUp() {
         springBoot = new SpringBoot(todoRepo);
     }

    @Test
    void countTodos(){
         springBoot.run((String) null);
         assertThat(todoRepo.count()).isEqualTo(3);
    }


}