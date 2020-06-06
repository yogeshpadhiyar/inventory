package com.neosoft.services;

import com.neosoft.modelhb.Product;
import com.neosoft.repositry.ProductRepositoryImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServicesImp implements ProductServices {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServicesImp.class);
    private final ProductRepositoryImp productRepositoryImp;

    @Autowired
    public ProductServicesImp(ProductRepositoryImp productRepositoryImp) {
        this.productRepositoryImp = productRepositoryImp;
    }


    @Override
    @Transactional
    public int insert(Product product) {
        LOGGER.debug("Inserting "+product);
        return productRepositoryImp.insert(product);
    }


    @Override
    @Transactional
    public List<Product> selectAll() {
        return productRepositoryImp.selectAll();
    }

    @Override
    @Transactional
    public Product selectById(int id) {
        LOGGER.debug("Selecting product by id is "+id);
        return productRepositoryImp.selectById(id);
    }

    @Override
    @Transactional
    public void update(Product product){
        LOGGER.debug("Updating "+product);
        productRepositoryImp.update(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        LOGGER.debug("Deleting product where id is "+product.getId());
        productRepositoryImp.delete(product);
    }

}
