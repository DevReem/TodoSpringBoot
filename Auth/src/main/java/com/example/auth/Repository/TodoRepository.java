package com.example.auth.Repository;

import com.example.auth.Model.MyUser;
import com.example.auth.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository <Todo,Integer> {
    List<Todo> findAllByMyUser(MyUser myUser);



    Todo findTodoById(Integer id);

//    void delete(Integer );
}
