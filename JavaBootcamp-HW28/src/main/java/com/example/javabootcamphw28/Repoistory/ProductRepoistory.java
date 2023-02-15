package com.example.javabootcamphw28.Repoistory;

import com.example.javabootcamphw28.Model.Orders;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepoistory extends JpaRepository<Product, Integer> {

    Product findProductById(Integer id);

    List<Product> findAllByMyUser(MyUser myUser);

}
