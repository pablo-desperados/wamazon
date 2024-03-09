package com.wamazon.app.Model;
import jakarta.persistence.*;

@Inheritance
@Entity
abstract public class BaseProductModel implements BaseProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private double price;
    public BaseProductModel(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }


    public double getPrice() {
        return this.price;
    }
    
    public abstract String getDescription();
    	
}
    