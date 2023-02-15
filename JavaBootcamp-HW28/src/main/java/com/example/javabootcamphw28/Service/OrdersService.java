package com.example.javabootcamphw28.Service;

import com.example.javabootcamphw28.Exception.ApiException;
import com.example.javabootcamphw28.Model.Orders;
import com.example.javabootcamphw28.Model.Product;
import com.example.javabootcamphw28.Repoistory.MyUserRepoistory;
import com.example.javabootcamphw28.Repoistory.OrdersRepoistory;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Repoistory.ProductRepoistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepoistory ordersRepoistory;


    private final MyUserRepoistory myUserRepoistory;
    private final ProductRepoistory productRepoistory;

    // CRUD

    //get
    public List<Orders> getOrders(Integer user_Id){
        MyUser myUser = myUserRepoistory.findMyUserById(user_Id);
            return ordersRepoistory.findAllByMyUser(myUser);
    }

    //add
    public void addOrder(Integer id, Orders orders,Integer product_id){
        MyUser user = myUserRepoistory.findMyUserById(id);
        Product product = productRepoistory.findProductById(product_id);
        if (orders ==null || product ==null ) {
            throw new ApiException("Id is not found");
        }
        orders.setProduct(product);
        orders.setStatus("New");
        orders.setTotalPrice(orders.getQuantity()*product.getPrice());
        orders.setMyUser(user);
        ordersRepoistory.save(orders);
    }

    public void assignOrder(Integer user_id, Integer orderId, Integer productId){
        Orders orders = ordersRepoistory.findOrdersById(orderId);
        Product product = productRepoistory.findProductById(productId);

        orders.setProduct(product);
        ordersRepoistory.save(orders);
    }

    // update
    public void updateOrder(Integer orderId, Orders orders, Integer userId){
        MyUser myUser = myUserRepoistory.findMyUserById(userId);
        Orders currentOrders = ordersRepoistory.findOrdersById(orderId);
        if(currentOrders ==null){
            throw new ApiException("Order could not be founded");
        }else if(currentOrders.getMyUser().getId()!=userId){
            throw new ApiException("Your not allowed to update this Order!!!");
        }
//        currentOrders.setId(orderId);
//        currentOrders.setMyUser(myUser);
        currentOrders.setStatus(orders.getStatus());
        currentOrders.setQuantity(orders.getQuantity());
        currentOrders.setTotalPrice(orders.getTotalPrice());
        currentOrders.setDateRecevied(orders.getDateRecevied());
        ordersRepoistory.save(currentOrders);
    }

    // delete

    public void deleteOrder(Integer orderId, Integer userId){
        Orders currentOrder = ordersRepoistory.findOrdersById(orderId);
        if(currentOrder==null){
            throw new ApiException("Blog could not be founded");
        }else if(currentOrder.getMyUser().getId()!=userId){
            throw new ApiException("Your not allowed to Delete this order!!!");
        }
        ordersRepoistory.delete(currentOrder);
    }


    //Create endpoint that change order status(only admin can change it)

    public void changeStatus(Integer user_id , Integer orderId , Orders orders){
        Orders currentOrder = ordersRepoistory.findOrdersById(orderId);
        MyUser myUser = myUserRepoistory.findMyUserById(user_id);
        if(currentOrder==null){
            throw new ApiException("Order could not be founded");
        }else if(myUser.getRole().equals("CUSTOMER")){
            throw new ApiException("Your not allowed to update this order!!!");
        }
        currentOrder.setStatus(orders.getStatus());
        ordersRepoistory.save(currentOrder);
    }

    public Orders findOrderByID(Integer user_id ,Integer orderid){
        MyUser myUser = myUserRepoistory.findMyUserById(user_id);
        Orders orders = ordersRepoistory.findOrdersById(orderid);
        if(orders==null){
            throw new ApiException("Order could not be founded");
        }else if(orders.getMyUser().getId()!=user_id && myUser.getRole().equals("CUSTOMER")) {
            throw new ApiException("Your not allowed to Update this Product!!!");
        }

        return orders;
    }





}