package com.example.javabootcamphw28.Service;

import com.example.javabootcamphw28.Exception.ApiException;
import com.example.javabootcamphw28.Model.MyUser;
import com.example.javabootcamphw28.Model.Product;
import com.example.javabootcamphw28.Repoistory.MyUserRepoistory;
import com.example.javabootcamphw28.Repoistory.ProductRepoistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepoistory productRepoistory;
    private final MyUserRepoistory myUserRepoistory;


    // CRUD

    //get
    public List<Product> getProduct(Integer user_Id){
        MyUser myUser = myUserRepoistory.findMyUserById(user_Id);
        return productRepoistory.findAllByMyUser(myUser);
    }

    //add
    public void addProduct(Integer user_Id, Product products){
        MyUser myUser = myUserRepoistory.findMyUserById(user_Id);
        products.setMyUser(myUser);
        productRepoistory.save(products);
    }

    // update
    public void updateProduct(Integer product_id, Product product, Integer userId){
        MyUser myUser = myUserRepoistory.findMyUserById(userId);
        Product currentProduct = productRepoistory.findProductById(product_id);
        if(currentProduct==null){
            throw new ApiException("Product could not be founded");
        }else if(currentProduct.getMyUser().getId()!=userId){
            throw new ApiException("Your not allowed to Update this Product!!!");
        }
        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        productRepoistory.save(currentProduct);
    }

    // delete

    public void deleteProduct(Integer productid, Integer userId){
        Product currentProduct = productRepoistory.findProductById(productid);
        if(currentProduct==null){
            throw new ApiException("Product could not be founded");
        }else if(currentProduct.getMyUser().getId()!=userId){
            throw new ApiException("Your not allowed to Delete this Product!!!");
        }
        productRepoistory.delete(currentProduct);
    }

    public Product findProductByID(Integer user_id ,Integer product_id){
        MyUser myUser = myUserRepoistory.findMyUserById(user_id);
        Product currentProduct = productRepoistory.findProductById(product_id);
        if(currentProduct==null){
            throw new ApiException("Product could not be founded");
        }else if(currentProduct.getMyUser().getId()!=user_id && myUser.getRole().equals("CUSTOMER")) {
            throw new ApiException("Your not allowed to Update this Product!!!");
        }

        return currentProduct;
    }






}
