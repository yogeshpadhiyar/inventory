package com.neosoft.modelhb;

import com.neosoft.validator.validationAnnotation.RangeCustom;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "product")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = "[a-zA-z]+" , message = "Enter Valid Name (Without Number)")
    private String name;
    @Pattern(regexp = "[a-zA-z]+" , message = "Enter Valid Name (Without Number)")
    private String category;
    @RangeCustom
    private int price;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
