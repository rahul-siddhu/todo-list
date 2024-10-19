package com.testProjects.todolist.repositories;

import com.testProjects.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    //This is a type of naming convention in Spring data framework which means find user entity in task where username of user
    // is the given parameter
    List<Task> findByUserUsername(String username);
}
