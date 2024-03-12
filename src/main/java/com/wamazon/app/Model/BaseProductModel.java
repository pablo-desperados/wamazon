package com.wamazon.app.Model;
import jakarta.persistence.*;

@Inheritance
@Entity
public class BaseProductModel implements BaseProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private double price;
    private String description;
    public BaseProductModel(){};
    public BaseProductModel(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    
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
    
    public String getDescription() {
		return this.description;
	}
    	
}
    