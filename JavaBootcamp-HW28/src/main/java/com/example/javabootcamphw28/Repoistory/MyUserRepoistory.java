package com.example.javabootcamphw28.Repoistory;

import com.example.javabootcamphw28.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepoistory extends JpaRepository<MyUser, Integer> {
    MyUser findMyUserById(Integer id);
}
