package com.neosoft.services;

import com.neosoft.modelhb.Product;

import java.util.List;

public interface ProductServices {
     int insert(Product product);
     void update(Product product);
     void delete(Product product);
     List<Product> selectAll();
     Product selectById(int id);
}
