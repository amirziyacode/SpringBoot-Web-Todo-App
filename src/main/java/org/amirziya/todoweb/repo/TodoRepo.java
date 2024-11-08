package org.amirziya.todoweb.repo;

import org.amirziya.todoweb.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface TodoRepo extends JpaRepository<Todo,UUID> {

}
