package com.example.auth;

import com.example.auth.Model.MyUser;
import com.example.auth.Model.Todo;
import com.example.auth.Repository.AuthRepository;
import com.example.auth.Repository.TodoRepository;
import com.example.auth.Service.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
   // MockitoExtension << تعامل معاها وكأنها مخفيه, اغلف جزئيه محدده
public class TodoServiceTests {


    @InjectMocks
    TodoService todoService;

    @Mock
    AuthRepository authRepository;

    @Mock
    TodoRepository todoRepository;


    Todo todo1,todo2,todo3;
    List<Todo> todos;
    MyUser myUser;



    @BeforeEach
    void setUp() {
        myUser=new MyUser(null,"Reem" , "12345" , "ADMIN" , null);
        todo1 = new Todo(null , "todo1",  myUser );
        todo2 = new Todo(null , "todo2", myUser );
        todo3 = new Todo(null , "todo3",  myUser );



        todos=new ArrayList<>();
        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);
    }
    @Test
    public void getTodos(){
        when(todoRepository.findAll()).thenReturn(todos);
        List<Todo> todoList=todoService.getTodos();
        Assertions.assertEquals(todoList,todos);
        Assertions.assertEquals(3,todoList.size());


        verify(todoRepository,times(1)).findAll();

    }


    @Test
    public void getTodo(){
        when(authRepository.findMyUserById(myUser.getId())).thenReturn(myUser);
        when(todoRepository.findAllByMyUser(myUser)).thenReturn(todos);
        List<Todo>todoList=todoService.getTodo(myUser.getId());
        Assertions.assertEquals(todoList.get(0).getMyUser().getId(),myUser.getId());
        verify(authRepository,times(1)).findMyUserById(myUser.getId());
        verify(todoRepository,times(1)).findAllByMyUser(myUser);
    }

    @Test
    public void addTodo(){
        when(authRepository.findMyUserById(myUser.getId())).thenReturn(myUser);
//      when(todoRepository.findTodoById(todo1.getId())).thenReturn(todos);
        todoService.addTodo(myUser.getId(),todo1);
        //Assertions فيه شي يرجع لي والميثود هنا فويد ما نستخدم
     // Assertions.assertEquals();
     verify(authRepository,times(1)).findMyUserById(myUser.getId());
     verify(todoRepository,times(1)).save(todo1);
    }

    @Test
    public void updateTodo(){
        when(todoRepository.findTodoById(todo1.getId())).thenReturn(todo1);
        when(authRepository.findMyUserById(myUser.getId())).thenReturn(myUser);

        todoService.updateTodo(todo1.getId(),todo2,myUser.getId());

      verify(todoRepository,times(1)).findTodoById(todo1.getId());

      verify(authRepository,times(1)).findMyUserById(myUser.getId());

    }
  @Test
  public void deleteTodo(){
        when(todoRepository.findTodoById(todo1.getId())).thenReturn(todo1);
        todoService.deleteTodo(todo1.getId(),myUser.getId());
        verify(todoRepository,times(1)).findTodoById(todo1.getId());
  }


}
