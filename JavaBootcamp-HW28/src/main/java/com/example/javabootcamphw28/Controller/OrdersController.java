package com.example.javabootcamphw28.Controller;

import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Model.Orders;
import com.example.javabootcamphw28.Model.Product;
import com.example.javabootcamphw28.Service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrdersController {

    private final OrdersService ordersService;

    // Get
    @GetMapping("/get")
    public ResponseEntity getAllOrders(@AuthenticationPrincipal MyUser myUser){
        List<Orders> orders = ordersService.getOrders(myUser.getId());
        return ResponseEntity.status(200).body(orders);
    }


    // Add
    @PostMapping("/add/productID/{product_id}")
    public ResponseEntity addNewOrders(@AuthenticationPrincipal MyUser myUser,@Valid @RequestBody Orders Orders, @PathVariable Integer product_id){
        // user id
        ordersService.addOrder(myUser.getId(), Orders,product_id);
        return ResponseEntity.status(200).body("Orders have been added");
    }

    // Assign
    @PutMapping("/assign/{orderId}/{productId}")
    public ResponseEntity assignOrderToProduct(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer orderId,@PathVariable Integer productId){
        // user id
        ordersService.assignOrder(myUser.getId(),orderId,productId);
        return ResponseEntity.status(200).body("Orders have been assigned");
    }

    // Update
    @PutMapping("/update/{Orders_id}")
    public ResponseEntity updateNewOrders(@AuthenticationPrincipal MyUser myUser, @Valid @RequestBody Orders Orders, @PathVariable Integer Orders_id){
        // user id
        ordersService.updateOrder(Orders_id, Orders, myUser.getId());
        return ResponseEntity.status(200).body("Orders have been updated");
    }
    // Delete
    @DeleteMapping("/delete/{Orders_id}")
    public ResponseEntity deleteALlOrders(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer Orders_id){
        ordersService.deleteOrder(Orders_id, myUser.getId());
        return ResponseEntity.status(200).body("Orders have been deleted");

    }
    // change status
    @PutMapping("/changeStatus/{order_id}")
    public ResponseEntity ChangeStatus(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer order_id, @Valid @RequestBody Orders orders){
        ordersService.changeStatus(myUser.getId(), order_id,orders);
        return ResponseEntity.status(200).body("status have changed ");
    }


    /// find by id
    @GetMapping("/findByid/{order_id}")
    public ResponseEntity findById(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer order_id){
        Orders orders = ordersService.findOrderByID(myUser.getId(), order_id);
        return ResponseEntity.status(200).body(orders);
    }

}
