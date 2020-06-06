package com.neosoft.services;

import com.neosoft.model.Invoice;

import com.neosoft.repositry.OrderDetailsRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServicesImp implements OrderDetailsServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetailsRepositoryImpl.class);
    private final OrderDetailsRepositoryImpl orderDetailsRepositoryImpl;
    @Autowired
    public OrderDetailsServicesImp(OrderDetailsRepositoryImpl orderDetailsRepositoryImpl ) {
        this.orderDetailsRepositoryImpl= orderDetailsRepositoryImpl;
    }

    /*@Override
    public int placeInOrderDetails(List<Product> productList , int orderId) {
        productList.forEach(e->logger.debug("Inserting "+e));
        int affectedRow=0;
        for (Product product : productList) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderId(orderId);
            orderDetails.setProductId(product.getId());
            orderDetails.setQuantity(Cart.cart.get(product.getId()));
            orderDetails.setPrice(Cart.cart.get(product.getId()) * product.getPrice());
            affectedRow += orderDetailsRepoImp.insert(orderDetails);
        }
        logger.info("All product insert successfully.");
        logger.debug("Inserted orders is "+affectedRow);
        return affectedRow;
    }*/

    @Override
    public List<Invoice> getInvoice(int orderId) {
        LOGGER.info("In order details service.");
        return orderDetailsRepositoryImpl.getInvoiceByOrderId(orderId);
    }
}
