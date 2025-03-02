package org.amirziya.todoweb.repo;

import org.amirziya.todoweb.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepo extends JpaRepository<Todo,Integer> {
    @NonNull
    Optional<Todo> findById(@NonNull Integer todoId);

    @Query(value ="SELECT * FROM todos WHERE completed = true",nativeQuery=true)
    List<Todo> findCompleteTasks();

    @Query(value = "SELECT * FROM todos WHERE completed = false",nativeQuery = true)
    List<Todo> findNotCompleteTasks();
}
