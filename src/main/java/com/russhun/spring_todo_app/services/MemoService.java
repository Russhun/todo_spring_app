package com.russhun.spring_todo_app.services;

import com.russhun.spring_todo_app.models.Memo;
import com.russhun.spring_todo_app.models.User;
import com.russhun.spring_todo_app.repositories.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


// TODO update it
@Deprecated
public class MemoService {

    @Autowired
    private MemoRepository memoRepository;

    public MemoService(){}

    public Memo findMemoById(int id){return memoRepository.findById(id);}

    public void save(Memo memo) {memoRepository.save(memo);}

    public void delete(Memo memo) {memoRepository.delete(memo);}

    public List findAllMemoByUser(User user) {return memoRepository.findAllByUser(user);}

}
