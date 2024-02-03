package com.example.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.DAO.GenericDAO;
import com.example.DAOImpl.ProductDaoImpl;
import com.example.DTO.ProductDTO;
import com.example.Mapper.ProductMapper;
import com.example.Model.Product;
import com.example.ServiceImpl.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")

public class ProductController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    GenericDAO<Product, Integer> productDao = new ProductDaoImpl();
    ProductServiceImpl productService = new ProductServiceImpl(productDao);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdParam = request.getParameter("productId");

        if (productIdParam != null && !productIdParam.isEmpty()) {
            try {
                Integer productId = Integer.parseInt(productIdParam);
                Optional<Product> optionalProduct = productService.getProductById(productId);
                if (optionalProduct.isPresent()) {
                    ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDto(optionalProduct.get());
                    response.getWriter().write("ProductDTO: " + productDTO.toString());
                    logger.info("Product listed by id" + productId + " " + optionalProduct);
                } else {
                    response.getWriter().write("Product not found");
                    logger.info("Product not found");
                }
            } catch (NumberFormatException e) {
                response.getWriter().write("Invalid product ID format");
            } catch (ClassNotFoundException e) {
                response.getWriter().write("Error retrieving product: " + e.getMessage());
            }
        } else {
            try {
                List<Product> products = productService.getAllProducts();
                response.getWriter().write("All products: " + products.toString());
                logger.info("All products are listed");
            } catch (ClassNotFoundException e) {
                response.getWriter().write("Error retrieving products: " + e.getMessage());
            }
        }
    }
}
