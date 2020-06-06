package com.neosoft.services;

import com.neosoft.model.Invoice;
import com.neosoft.modelhb.Order;
import com.neosoft.modelhb.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderServices {
    List<Product> selectAll();
    Order selectById(int orderId);
    void addToCart(int[] productIds, int[] quantity);
    List<Product> cartProducts();
    List<Invoice> getInvoice(int orderId);
    List<Invoice> printInvoice(List<Product> productList);
    Order placeInOrderHB(List<Product> productList);

    //Order placeInOrder();
    //int placeInOrderDetails(List<Product> productList , int orderId);
}
