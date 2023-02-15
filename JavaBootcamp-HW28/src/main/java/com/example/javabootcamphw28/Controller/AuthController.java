package com.example.javabootcamphw28.Controller;


import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Service.AuthService;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    // All

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MyUser myUser){
        authService.register(myUser);
        return ResponseEntity.status(200).body("User have been registered!");
    }

    // All

    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("Welcome Customer");
    }

    // All
    @PutMapping("/update/{id}")
    public ResponseEntity UpdateUser(@Valid @RequestBody MyUser user, @AuthenticationPrincipal MyUser myUser, @PathVariable Integer id){
        authService.UpdateUser(myUser.getId(), user);
        return ResponseEntity.status(200).body("user have been updated");
    }

    // All

    @DeleteMapping("/delete/{id}")
    public ResponseEntity DeleteUser( @AuthenticationPrincipal MyUser myUser,@PathVariable Integer id){
        authService.delete(id);
        return ResponseEntity.status(200).body("user have been deleted!");
    }

    // Get Customer by customer
    @GetMapping("/get")
    public ResponseEntity GetUserbyid( @AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(authService.GetUser(myUser.getId()));
    }

    // Get all customers by admin
    // Admin
    @GetMapping("/all-users")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    // get by Id

    @GetMapping("/get/{User_id}")
    public ResponseEntity GetUserbyid( @AuthenticationPrincipal MyUser myUser, @PathVariable Integer User_id){
        MyUser user = authService.GetUser(User_id);
        return ResponseEntity.status(200).body(user);
    }


}