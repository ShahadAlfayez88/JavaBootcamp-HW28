package com.example.javabootcamphw28.Controller;

import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Model.Product;
import com.example.javabootcamphw28.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    // Get
    @GetMapping("/get")
    public ResponseEntity getProduct(@AuthenticationPrincipal MyUser myUser){
        List<Product> products = productService.getProduct(myUser.getId());
        return ResponseEntity.status(200).body(products);
    }


    // Add
    @PostMapping("/add")
    public ResponseEntity addProduct(@AuthenticationPrincipal MyUser myUser,@Valid @RequestBody Product Product){
        // user id
        productService.addProduct(myUser.getId(),Product);
        return ResponseEntity.status(200).body("Product have been added");
    }

    // Update
    @PutMapping("/update/{Product_id}")
    public ResponseEntity updateProduct(@AuthenticationPrincipal MyUser myUser,@Valid @RequestBody Product Product,@PathVariable Integer Product_id){
        // user id
        productService.updateProduct(Product_id,Product, myUser.getId());
        return ResponseEntity.status(200).body("Product have been updated");
    }
    // Delete
    @DeleteMapping("/delete/{Product_id}")
    public ResponseEntity deleteProduct(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer Product_id){
        productService.deleteProduct(Product_id, myUser.getId());
        return ResponseEntity.status(200).body("Product have been deleted");

    }

    @GetMapping("/findByid/{product_id}")
    public ResponseEntity findById(@AuthenticationPrincipal MyUser myUser,@PathVariable Integer product_id){
        Product product = productService.findProductByID(myUser.getId(), product_id);
        return ResponseEntity.status(200).body(product);
    }
}
