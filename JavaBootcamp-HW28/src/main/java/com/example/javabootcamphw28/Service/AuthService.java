package com.example.javabootcamphw28.Service;

import com.example.javabootcamphw28.Exception.ApiException;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Repoistory.AuthRepoistory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepoistory authRepoistory;

    public void register(MyUser myUser){
        String hashedPassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashedPassword);
        authRepoistory.save(myUser);
    }

    public void delete(Integer userId){
        MyUser myUser = authRepoistory.findMyUsersById(userId);
        if(myUser==null){
            throw new ApiException("myUser could not be founded");
        }
        authRepoistory.delete(myUser);
    }

    public MyUser GetUser(Integer user_id){
        MyUser user = authRepoistory.findMyUsersById(user_id);
        if(user==null){
            throw new ApiException("user id not found");
        }
        return user;
    }

    public void UpdateUser(Integer user_id,MyUser user){
        MyUser old_user= authRepoistory.findMyUsersById(user_id);
        old_user.setUsername(user.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(old_user.getPassword()));
        old_user.setRole(user.getRole());
        authRepoistory.save(old_user);
    }

        public List<MyUser> getAllUsers(){
            return authRepoistory.findAll();
    }
}
