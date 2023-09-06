package com.example.auth.Service;

import com.example.auth.Model.MyUser;
import com.example.auth.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    public List<MyUser> getAllUser(){
        return authRepository.findAll();
    }

    public void register(MyUser myUser){
        // يطلع لي الباسوورد مشفر
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setRole(hash);
        myUser.setRole("USER");
        authRepository.save(myUser);
    }
}
