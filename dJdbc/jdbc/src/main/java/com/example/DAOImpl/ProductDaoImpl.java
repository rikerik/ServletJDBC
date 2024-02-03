package com.example.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.DAO.GenericDAO;
import com.example.DB.DbConnector;
import com.example.Model.Product;

public class ProductDaoImpl implements GenericDAO<Product, Integer> {

    @Override
    public void save(Product product) throws ClassNotFoundException {
        String sql = "INSERT INTO products (product_name, supplier, price, quantity, created_by_user_id)"
                +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbConnector.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getSupplier());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getCreatedByUserId());
            // Created_on generates automatically
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Product> get(Integer productId) throws ClassNotFoundException {
        String sql = "SELECT product_id, product_name, supplier, price, quantity, created_by_user_id, created_on, modified_by_user_id, modified_on FROM products WHERE product_id = ?";
        Optional<Product> product = null;

        try (Connection conn = DbConnector.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                String supplier = rs.getString("supplier");
                double price = rs.getDouble("price");
                Integer quantity = rs.getInt("quantity");
                Integer createdByUserId = rs.getInt("created_by_user_id");
                Timestamp createdOn = rs.getTimestamp("created_on");
                Integer modifiedByUserId = rs.getInt("modified_by_user_id");
                Timestamp modifiedOn = rs.getTimestamp("modified_on");

                product = Optional.of(new Product(id, productName, supplier, price, quantity, createdByUserId,
                        createdOn, modifiedByUserId, modifiedOn));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAll() throws ClassNotFoundException {
        List<Product> productList = new ArrayList<Product>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DbConnector.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setSupplier(rs.getString("supplier"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCreatedByUserId(rs.getInt("created_by_user_id"));
                product.setCreatedOn(rs.getTimestamp("created_on"));
                product.setModifiedByUserId(rs.getInt("modified_by_user_id"));
                product.setModifiedOn(rs.getTimestamp("modified_on"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public void update(Integer id, Product updatedProduct) throws ClassNotFoundException {
        String sql = "UPDATE products SET product_name =?, supplier =?, price =?, quantity =?, modified_by_user_id =?, modified_on = ? WHERE product_id = ?";
        try (Connection conn = DbConnector.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, updatedProduct.getProductName());
            preparedStatement.setString(2, updatedProduct.getSupplier());
            preparedStatement.setDouble(3, updatedProduct.getPrice());
            preparedStatement.setInt(4, updatedProduct.getQuantity());
            preparedStatement.setInt(5, updatedProduct.getModifiedByUserId());
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws ClassNotFoundException {
        String sql = "DELETE FROM products WHERE product_id = ?";

        try (Connection conn = DbConnector.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
