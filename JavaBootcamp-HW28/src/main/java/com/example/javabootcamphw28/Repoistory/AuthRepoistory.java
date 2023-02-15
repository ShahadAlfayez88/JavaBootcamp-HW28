package com.example.javabootcamphw28.Repoistory;

import com.example.javabootcamphw28.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepoistory extends JpaRepository<MyUser, Integer> {
    MyUser findMyUserByUsername(String username);

    MyUser findMyUsersById(Integer id);

}
