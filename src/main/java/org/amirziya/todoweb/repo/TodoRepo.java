package org.amirziya.todoweb.repo;

import org.amirziya.todoweb.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<Todo,Integer> {
    Todo findById(int dd);
}
