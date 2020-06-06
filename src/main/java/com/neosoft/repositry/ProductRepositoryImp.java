package com.neosoft.repositry;

import com.neosoft.modelhb.Product;
import com.neosoft.util.Formatter;
import com.neosoft.util.QueryProperty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImp implements Repo<Product> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImp.class);

    private final SessionFactory sessionFactory;
    private final QueryProperty queryProperty;

    @Autowired
    public ProductRepositoryImp(SessionFactory sessionFactory , QueryProperty queryProperty) {
        this.sessionFactory = sessionFactory;
        this.queryProperty = queryProperty;
    }

    @Override
    public Session databaseConnector() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int insert(Product product) {
        LOGGER.debug("Inserting "+product);
        int affectedRow;
        Session session = databaseConnector();
        affectedRow = Formatter.convertToInt(session.save(product));
        LOGGER.debug("New "+affectedRow+"Product inserted.");
        LOGGER.info("Product successfully inserted.");
        return affectedRow;
    }


    @Override
    public void update(Product product) {
        LOGGER.debug("Updating "+product);
        Session session = databaseConnector();
        session.update(product);
        LOGGER.debug("Product updated.");
        LOGGER.info("Product successfully updated.");

    }

    @Override
    public void delete(Product product) {
        LOGGER.debug("Deleting product id is "+product.getId());
        Session session = databaseConnector();
        session.delete(product);
        LOGGER.info("product successfully deleted.");
    }


    @Override
    public List<Product> selectAll() {
        List<Product> productList;
        Session session = databaseConnector();
        Query query = session.createQuery(queryProperty.query("product.select.all"));
        productList = query.list();
        productList.forEach(e-> LOGGER.debug("Selected Products is "+e));
        LOGGER.info("Select all products successfully.");
        return productList;

    }

    @Override
    public Product selectById(int id) {
        LOGGER.info("Selecting product by id is "+id);
        Product product;
        Session session = databaseConnector();
        Query query = session.createQuery(queryProperty.query("product.select.by.id"));
        query.setParameter("id", id);
        product = (Product) query.list().get(0);
        LOGGER.debug("Selected Product is "+product);
        return product;
    }
}
