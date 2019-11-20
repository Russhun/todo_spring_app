package com.russhun.spring_todo_app.repositories;

import com.russhun.spring_todo_app.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {

    User findByName(String name);

}
