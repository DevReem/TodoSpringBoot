package com.example.auth.Repository;

import com.example.auth.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<MyUser, Integer> {
    MyUser findUserByUsername(String username);

    MyUser findMyUserById(Integer userId);
}
