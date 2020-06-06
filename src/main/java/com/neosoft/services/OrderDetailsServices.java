package com.neosoft.services;

import com.neosoft.model.Invoice;
import com.neosoft.modelhb.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailsServices {
    //int placeInOrderDetails(List<Product> productList , int orderId);
    List<Invoice> getInvoice(int orderId);
}
