package com.wamazon.app;

import com.wamazon.app.Model.BaseProductModel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCartBuilder {
	ShoppingCart cart;

    public ShoppingCartBuilder() {
    	this.cart = ShoppingCart.getInstance();
    }

    public ShoppingCartBuilder addItem(BaseProductModel addedProductModel) {
        if (addedProductModel != null) {
            UUID uuid = UUID.randomUUID();
            
            this.cart.getItems().put(uuid, addedProductModel);
            this.cart.setItemCount(this.cart.getItemCount()+1);
        }
        return this;
    }

    public ShoppingCartBuilder removeItem(UUID id) {
    	this.cart.getItems().remove(id);
    	this.cart.setItemCount(this.cart.getItemCount()-1);
        return this;
    }

    public ShoppingCart build() {
        return this.cart;
    }
}
