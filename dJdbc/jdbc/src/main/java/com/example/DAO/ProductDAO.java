package com.example.DAO;

import java.util.List;

import com.example.Model.Product;

public interface ProductDAO {
    void addProduct(Product product) throws ClassNotFoundException;

    Product getProductById(Integer productId) throws ClassNotFoundException;

    List<Product> getAllProducts() throws Exception;

    void update(Integer id, Product product) throws ClassNotFoundException;

    void delete(Integer id) throws ClassNotFoundException;
}
