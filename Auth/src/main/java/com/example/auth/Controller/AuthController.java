package com.example.auth.Controller;

import com.example.auth.Model.MyUser;
import com.example.auth.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")

public class AuthController {

    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAll(){
        List<MyUser> myUserList=authService.getAllUser();
        return ResponseEntity.status(200).body(myUserList);
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MyUser myUser){
        authService.register(myUser);
        return ResponseEntity.status(200).body("User register ");
    }

    @PostMapping("/admin")
    public ResponseEntity Admin(){
        return ResponseEntity.status(200).body("Welcome Admin");
    }

    @PostMapping("/user")
    public ResponseEntity User(){
        return ResponseEntity.status(200).body("Welcome User");
    }


    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("LogIn");
    }


    @PostMapping("/logout")
    public ResponseEntity Logout(){
        return ResponseEntity.status(200).body("LogOut");
    }


}
