package com.neosoft.controller;

import com.neosoft.modelhb.Product;
import com.neosoft.services.ProductServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/ProductController")
public class ProductController{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductServices productServices;
    @Autowired
    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping(value = "/home")
    public ModelAndView index(){
        LOGGER.info("Enter In Inventory System.");
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/ProductManage")
    public ModelAndView productManage(){
        LOGGER.info("Admin at product management portal.");
        return new ModelAndView("productManage");
    }

    @GetMapping("/insertPage")
    public ModelAndView insertPage(){
        LOGGER.info("Trying to insert new product.");
        return new ModelAndView( "insertProduct" , "product",  new Product() );
    }

    @PostMapping("/insert")
    public ModelAndView insert(@Valid @ModelAttribute Product product , BindingResult bs){
        if(bs.hasErrors()){
            LOGGER.warn("System not support any invalid input. Please enter valid input.");
            return new ModelAndView("insertProduct");
        }
        else{
            LOGGER.debug("New Product"+product+" insert into product table.");
            int affectedRow = productServices.insert(product);
            LOGGER.debug("New "+affectedRow +" Product Insert");
            return new ModelAndView("redirect:/ProductController/insertPage");
        }

    }

    @GetMapping("/selectAll")
    public ModelAndView selectAll(){
        LOGGER.info("Load all products");
        List<Product> productList = productServices.selectAll();
        productList.forEach(e-> LOGGER.debug(String.valueOf(e)));
        return new ModelAndView("viewProduct" , "productList" , productList);
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam int productId){
        LOGGER.info("Trying to edit product");
        LOGGER.debug("Edit product id is"+productId);
        return new ModelAndView("editProduct", "product" ,productServices.selectById(productId));

    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute Product product , BindingResult bs){
        if(bs.hasErrors()){
            LOGGER.warn("System not support any invalid input. Please enter valid input.");
            return new ModelAndView("insertProduct");
        }
        else {
            LOGGER.debug("Updating "+product);
            productServices.update(product);
            //logger.debug("Updated product no is "+affectedRow);
            return new ModelAndView("redirect:/ProductController/selectAll");
        }
    }

    @GetMapping("/delete")
    public ModelAndView delete(@ModelAttribute Product product, @RequestParam int productId){
        LOGGER.info("System trying to delete product");
        product.setId(productId);
        LOGGER.debug("Deleting product id is "+product.getId());
        productServices.delete(product);
        LOGGER.debug("Product id "+product.getId() +"was deleted.");
        return new ModelAndView("redirect:/ProductController/selectAll");

    }

}
