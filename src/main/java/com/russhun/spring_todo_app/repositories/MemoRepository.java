package com.russhun.spring_todo_app.repositories;

import com.russhun.spring_todo_app.models.Memo;
import com.russhun.spring_todo_app.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface MemoRepository extends CrudRepository<Memo, Integer> {

    ArrayList<Memo> findAllByUser(User user);
}
