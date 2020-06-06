package com.neosoft.repo;

import com.neosoft.config.DatabaseConfiguration;
import com.neosoft.modelhb.Product;
import com.neosoft.util.QueryProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepoImp implements Repo<Product> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepoImp.class);
    private final DatabaseConfiguration databaseConfiguration;
    private final QueryProperty queryProperty;

    @Autowired
    public ProductRepoImp(DatabaseConfiguration databaseConfiguration , QueryProperty queryProperty){
        this.databaseConfiguration = databaseConfiguration;
        this.queryProperty = queryProperty;

    }


    @Override
    public Connection createConnection() throws SQLException, ClassNotFoundException {
        return databaseConfiguration.getConnection();
    }


    @Override
    public int insert(Product product) {
        LOGGER.debug("Inserting "+product);
        int affectedRow=0;
        try{
            Connection connection = createConnection();
            PreparedStatement ps = connection.prepareStatement(queryProperty.query("product.insert"));
            ps.setString(1 ,product.getName());
            ps.setString(2, product.getCategory());
            ps.setInt(3, product.getPrice());
            affectedRow= ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Database connection failed Or May be Driver class not found.");
            e.printStackTrace();
        }
        LOGGER.debug("New "+affectedRow+"Product inserted.");
        LOGGER.info("Product successfully inserted.");
        return affectedRow;
    }


    @Override
    public int update(Product product) {
        LOGGER.debug("Updating "+product);
        int affectedRow=0;
        try {
            Connection connection =createConnection();
            PreparedStatement ps = connection.prepareStatement(queryProperty.query("product.update.by.id"));
            ps.setString(1,product.getName());
            ps.setString(2,product.getCategory());
            ps.setInt(3,product.getPrice());
            ps.setInt(4,product.getId());
            affectedRow=ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Database connection failed Or May be Driver class not found.");
            e.printStackTrace();
        }
        LOGGER.debug("New "+affectedRow+"Product updated.");
        LOGGER.info("Product successfully updated.");
        return affectedRow;
    }


    @Override
    public void delete(Product product) {
        LOGGER.debug("Deleting product id is "+product.getId());
        try {
            Connection connection =createConnection();
            PreparedStatement ps = connection.prepareStatement(queryProperty.query("product.delete"));
            ps.setInt(1,product.getId());
            ps.execute();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Database connection failed Or May be Driver class not found.");
            e.printStackTrace();
        }
        LOGGER.info("product successfully deleted.");
    }


    @Override
    public List<Product> selectAll() {
        List<Product> productList = new ArrayList<>();
        Product product ;
        try {
            Connection connection = createConnection();
            PreparedStatement ps = connection.prepareStatement(queryProperty.query("product.select.all"));
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setCategory(rs.getString(3));
                product.setPrice(rs.getInt(4));
                productList.add(product);
            }
        }catch (SQLException | ClassNotFoundException e){
            LOGGER.error("Database connection failed Or May be Driver class not found.");
            e.printStackTrace();
        }
        productList.forEach(e-> LOGGER.debug("Selected Products is "+e));
        LOGGER.info("Select all products successfully.");
        return productList;

    }

    @Override
    public Product selectById(int id) {
        LOGGER.info("Selecting product by id is "+id);
        Product product = new Product();
        try{
            Connection connection = createConnection();
            PreparedStatement ps = connection.prepareStatement(queryProperty.query("product.select.by.id"));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setCategory(rs.getString(3));
                product.setPrice(rs.getInt(4));
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Database connection failed Or May be Driver class not found.");
            e.printStackTrace();
        }
        LOGGER.debug("Selected Product is "+product);
        return product;
    }
}
