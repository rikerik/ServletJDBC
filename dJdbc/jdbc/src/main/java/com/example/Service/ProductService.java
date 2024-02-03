package com.example.Service;

import java.util.List;
import java.util.Optional;

import com.example.Model.Product;

public interface ProductService {
    Optional<Product> getProductById(Integer productId) throws ClassNotFoundException;

    List<Product> getAllProducts() throws ClassNotFoundException;

    void addProduct(Product product) throws ClassNotFoundException;

    void updateProduct(Integer productId, Product product) throws ClassNotFoundException;

    void deleteProduct(Integer productId) throws ClassNotFoundException;

}
