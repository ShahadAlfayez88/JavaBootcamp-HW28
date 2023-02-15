package com.example.javabootcamphw28.Repoistory;

import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepoistory extends JpaRepository<Orders, Integer> {

    Orders findOrdersById(Integer id);

    List<Orders> findAllByMyUser(MyUser myUser);


}
