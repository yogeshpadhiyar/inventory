package com.neosoft.controller;

import com.neosoft.modelhb.Order;
import com.neosoft.modelhb.Product;
import com.neosoft.services.OrderServices;
import com.neosoft.util.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/OrderController")
@SessionAttributes("productList")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private final OrderServices orderServices;
    @Autowired
    public OrderController( OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @GetMapping("/home")
    public ModelAndView index(){
        LOGGER.info("Enter In Inventory System.");
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/orderManage")
    public ModelAndView orderManage(){
        LOGGER.info("User In Order management.");
        return new ModelAndView("orderManage");
    }

    @GetMapping("/selectProduct")
    public ModelAndView selectProduct(){
        LOGGER.info("Select all available product for sell.");
        List<Product> productList = orderServices.selectAll();
        return new ModelAndView("addToCart" , "productList" , productList);
    }

    @PostMapping("/addToCart")
    public ModelAndView addToCart(@RequestParam int[] productIds , int[] quantity){
        for (int i = 0; i < productIds.length; i++) {
            LOGGER.debug("In cart : product Id is "+productIds[i]+" Quantity is "+quantity[i]);
        }
        orderServices.addToCart(productIds , quantity);
        LOGGER.info("All products add successfully in cart");
        return new ModelAndView("redirect:/OrderController/orderManage");
    }

    @GetMapping("/viewCart")
    public ModelAndView viewCart(ModelMap modelMap){
        List<Product> productList = orderServices.cartProducts();
        productList.forEach(e-> LOGGER.debug("Product detail in cart :"+e));
        modelMap.addAttribute("productList",productList);
        modelMap.addAttribute("cart",Cart.cart);
        modelMap.addAttribute("totalPrice" , Cart.totalPrice);
        return new ModelAndView("viewCart");
    }

    @PostMapping("/placeOrder")
    public ModelAndView placeOrder(@SessionAttribute("productList") List<Product> productList,  ModelMap modelMap){
        LOGGER.info("Insert into Order table and generate order id");
        //Order order = orderServices.placeInOrder();
        Order order = orderServices.placeInOrderHB(productList);
        LOGGER.info("Insert order details into order details table.");
        //int insertedOrder = orderServices.placeInOrderDetails(productList , order.getId());
        //logger.debug("Inserted total order is "+insertedOrder);
        modelMap.addAttribute("order" , order);
        LOGGER.debug("Order : "+order);
        return new ModelAndView("viewInvoice" , "invoiceList" , orderServices.printInvoice(productList));

    }

    @GetMapping("/printInvoicePage")
    public ModelAndView printInvoicePage(){
        return new ModelAndView("printInvoice");
    }

    @PostMapping("/printInvoice")
    public ModelAndView printInvoice(@RequestParam int  orderId ,  ModelMap modelMap){
        LOGGER.info("Finding invoice...");
        Order order = orderServices.selectById(orderId);
        LOGGER.debug("Invoice order id is "+order.getId());
        modelMap.addAttribute("order", order);
        return new ModelAndView("viewInvoice","invoiceList", orderServices.getInvoice(orderId));
    }
}
