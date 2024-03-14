package com.wamazon.app.Model;
import java.net.URL;

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
    private String image;
    
    public BaseProductModel(){};
    public BaseProductModel(String name, double price, String description, String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
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
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    	
}
    