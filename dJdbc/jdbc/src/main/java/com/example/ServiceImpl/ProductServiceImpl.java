package com.example.ServiceImpl;

import java.util.List;
import java.util.Optional;

import com.example.DAO.GenericDAO;
import com.example.Model.Product;
import com.example.Service.ProductService;

public class ProductServiceImpl implements ProductService {
    private GenericDAO<Product, Integer> productDao;

    public ProductServiceImpl(GenericDAO<Product, Integer> productDao) {
        this.productDao = productDao;
    }

    @Override
    public Optional<Product> getProductById(Integer productId) throws ClassNotFoundException {
        return productDao.get(productId);
    }

    @Override
    public List<Product> getAllProducts() throws ClassNotFoundException {
        return (List<Product>) productDao.getAll();
    }

    @Override
    public void addProduct(Product product) throws ClassNotFoundException {
        productDao.save(product);
    }

    @Override
    public void updateProduct(Integer productId, Product product) throws ClassNotFoundException {
        productDao.update(productId, product);
    }

    @Override
    public void deleteProduct(Integer productId) throws ClassNotFoundException {
        productDao.delete(productId);
    }

}
