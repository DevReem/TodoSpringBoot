package com.example.auth.Service;

import com.example.auth.ApiException.ApiException;
import com.example.auth.Model.MyUser;
import com.example.auth.Model.Todo;
import com.example.auth.Repository.AuthRepository;

import com.example.auth.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private  final AuthRepository authRepository;
    public List<Todo> getTodos( ) {
        return todoRepository.findAll();
    }

    public List<Todo> getTodo( Integer userId) {
        MyUser myUser=authRepository.findMyUserById(userId);
        return todoRepository.findAllByMyUser(myUser);
    }
    public void addTodo( Integer userId, Todo todo) {
        MyUser myUser=authRepository.findMyUserById(userId);
        todo.setMyUser(myUser);
        todoRepository.save(todo);
    }

    public void updateTodo(Integer id , Todo newTodo , Integer auth){
        Todo oldTodo=todoRepository.findTodoById(id);
        MyUser myUser=authRepository.findMyUserById(auth);

        if (oldTodo==null){
            throw new ApiException("Todo not found");
        }else if(oldTodo.getMyUser().getId()!=auth){
            throw new ApiException("Sorry , You do not have the authority to update this Todo!");
        }

        newTodo.setId(id);
        newTodo.setMyUser(myUser);

        todoRepository.save(newTodo);
    }

    public void deleteTodo( Integer userId, Integer todoId) {
        Todo todo=todoRepository.findTodoById(todoId);

        if(todo.getMyUser().getId()!=userId){
            throw  new ApiException("you dont have authority");
        }

        todoRepository.delete(todo);
    }


}
