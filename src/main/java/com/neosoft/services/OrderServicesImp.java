package com.neosoft.services;

import com.neosoft.model.Invoice;
import com.neosoft.modelhb.Order;
import com.neosoft.modelhb.OrderDetails;
import com.neosoft.modelhb.Product;
import com.neosoft.repositry.OrderRepositoryImpl;
import com.neosoft.util.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServicesImp implements OrderServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServicesImp.class);
    private final ProductServices productServices;
    private final OrderDetailsServices orderDetailsServices;
    private final OrderRepositoryImpl orderRepositoryImpl;
    @Autowired
    public OrderServicesImp(ProductServices productServices, OrderRepositoryImpl orderRepositoryImpl, OrderDetailsServices orderDetailsServices) {
        this.productServices = productServices;
        this.orderRepositoryImpl = orderRepositoryImpl;
        this.orderDetailsServices = orderDetailsServices;
    }

    @Override
    public List<Product> selectAll() {
        return productServices.selectAll();
    }

    @Override
    public Order selectById(int orderId) {
        return orderRepositoryImpl.selectById(orderId);
    }

    @Override
    public void addToCart(int[] productIds , int[] quantity) {
        for (int i = 0; i < productIds.length; i++) {
            if(Cart.cart.containsKey(productIds[i])){
                int oldQuantity = Cart.cart.get(productIds[i]);
                Cart.cart.remove(productIds[i]);
                Cart.cart.put(productIds[i] , oldQuantity+quantity[i]);
            }
            else{
                Cart.cart.put(productIds[i] , quantity[i]);
            }
        }
        LOGGER.info("Successfully add in cart.");
    }

    @Override
    public List<Product> cartProducts() {
        int totalPrice = 0;
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : Cart.cart.entrySet()) {
            Product product = productServices.selectById(entry.getKey());
            totalPrice += entry.getValue() * product.getPrice();
            productList.add(product);
            LOGGER.debug("Product add in cart is :"+product);
        }
        Cart.totalPrice = totalPrice;
        LOGGER.info("Set total price of products in cart.");
        return productList;
    }


    @Override
    public Order placeInOrderHB(List<Product> productList) {
        LOGGER.info("IN side placeOrderHB");
        Order order = new Order();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        order.setDate(Date.valueOf(simpleDateFormat.format(date)));

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        OrderDetails orderDetails;
        for (Product product : productList) {
            orderDetails = new OrderDetails();
            orderDetails.setProduct(product);
            orderDetails.setOrder(order);
            orderDetails.setQuantity(Cart.cart.get(product.getId()));
            orderDetails.setPrice(Cart.cart.get(product.getId()) * product.getPrice());
            orderDetailsList.add(orderDetails);
        }
        order.setOrderDetailsList(orderDetailsList);
        int i = orderRepositoryImpl.insert(order);
        LOGGER.debug("output :"+i);
        return order;
    }


    @Override
    public List<Invoice> printInvoice(List<Product> productList) {
        ArrayList<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice;
        for (Product product : productList) {
            invoice = new Invoice();
            invoice.setProductName(product.getName());
            invoice.setQuantity(Cart.cart.get(product.getId()));
            invoice.setPrice(Cart.cart.get(product.getId()) * product.getPrice());
            invoiceList.add(invoice);
        }
        Cart.cart.clear();
        LOGGER.info("Print Invoice on screen.");
        invoiceList.forEach(e-> LOGGER.debug("Invoice details "+e));
        return invoiceList;
    }

    @Override
    public List<Invoice> getInvoice(int orderId) {
        return orderDetailsServices.getInvoice(orderId);
    }


    /*@Override
    public Order placeInOrder() {
        Order order = new Order();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        order.setDate(Date.valueOf(simpleDateFormat.format(date)));
        order.setId(orderRepositoryImpl.insert(order));
        return order;
    }*/

    /*@Override
    public int placeInOrderDetails(List<Product> productList , int orderId) {
        return orderDetailsServices.placeInOrderDetails(productList , orderId);
    }*/
}
