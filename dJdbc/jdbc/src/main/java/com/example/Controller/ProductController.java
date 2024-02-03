package com.example.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.DAO.GenericDAO;
import com.example.DAOImpl.ProductDaoImpl;
import com.example.DTO.ProductCreateDTO;
import com.example.DTO.ProductDTO;
import com.example.Mapper.ProductCreateMapper;
import com.example.Mapper.ProductMapper;
import com.example.Model.Product;
import com.example.ServiceImpl.ProductServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet", urlPatterns = { "/products", "/products/create", "/products/update",
        "/products/delete" })
public class ProductController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    GenericDAO<Product, Integer> productDao = new ProductDaoImpl(); // Replace with your actual implementation
    ProductServiceImpl productService = new ProductServiceImpl(productDao);

    // get product by id or else list all products
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String productIdParam = request.getParameter("productId");

            if (productIdParam != null && !productIdParam.isEmpty()) {
                int productId = Integer.parseInt(productIdParam);
                Optional<Product> optionalProduct = productService.getProductById(productId);

                if (optionalProduct.isPresent()) {
                    ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDto(optionalProduct.get());
                    response.getWriter().write("ProductDTO: " + productDTO.toString());
                    logger.info("Product listed by id: " + productId);
                } else {
                    response.getWriter().write("Product not found");
                    logger.info("Product not found for id: " + productId);
                }
            } else {
                List<Product> products = productService.getAllProducts();
                response.getWriter().write("All products: " + products.toString());
                logger.info("All products are listed");
            }
        } catch (NumberFormatException | ClassNotFoundException | IOException e) {
            response.getWriter().write("An error occurred: " + e.getMessage());
            logger.error("Exception occurred: " + e.getMessage());
        }
    }

    // create product
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productName = request.getParameter("productName");
        String supplier = request.getParameter("supplier");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        ProductCreateDTO productCreateDTO = new ProductCreateDTO();
        productCreateDTO.setProductName(productName);
        productCreateDTO.setSupplier(supplier);
        productCreateDTO.setPrice(price);
        productCreateDTO.setQuantity(quantity);

        Product newProduct = ProductCreateMapper.INSTANCE.ProductCreateDTOToProduct(productCreateDTO);

        try {
            productService.addProduct(newProduct);
            response.getWriter().write("Product created successfully");
            logger.info("Product created successfully");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            logger.error("Exception occurred: " + e.getMessage());
        }
    }

    // update product
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String productIdParam = request.getParameter("productId");
            String updatedName = request.getParameter("updatedName");
            String updatedSupplier = request.getParameter("updatedSupplier");
            double updatedPrice = Double.parseDouble(request.getParameter("updatedPrice"));
            int updatedQuantity = Integer.parseInt(request.getParameter("updatedQuantity"));

            if (productIdParam != null && !productIdParam.isEmpty()) {
                int productId = Integer.parseInt(productIdParam);
                Optional<Product> optionalProduct = productService.getProductById(productId);

                if (optionalProduct.isPresent()) {
                    Product productToUpdate = optionalProduct.get();
                    productToUpdate.setProductName(updatedName);
                    productToUpdate.setSupplier(updatedSupplier);
                    productToUpdate.setPrice(updatedPrice);
                    productToUpdate.setQuantity(updatedQuantity);

                    productService.updateProduct(productId, productToUpdate);

                    response.getWriter().write("Product updated successfully");
                    logger.info("Product updated successfully: " + productToUpdate.toString());
                } else {
                    response.getWriter().write("Product not found for id: " + productId);
                    logger.info("Product not found for id: " + productId);
                }
            } else {
                response.getWriter().write("Invalid product ID");
                logger.info("Invalid product ID");
            }
        } catch (NumberFormatException | ClassNotFoundException | IOException e) {
            response.getWriter().write("An error occurred: " + e.getMessage());
            logger.error("Exception occurred: " + e.getMessage());
        }
    }

    // delete prdocut
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String productIdParam = request.getParameter("productId");

            if (productIdParam != null && !productIdParam.isEmpty()) {
                int productId = Integer.parseInt(productIdParam);
                productService.deleteProduct(productId);

                response.getWriter().write("Product deleted successfully");
                logger.info("Product deleted successfully with ID: " + productId);
            } else {
                response.getWriter().write("Invalid product ID");
                logger.info("Invalid product ID");
            }
        } catch (NumberFormatException | ClassNotFoundException | IOException e) {
            response.getWriter().write("An error occurred: " + e.getMessage());
            logger.error("Exception occurred: " + e.getMessage());
        }
    }

}