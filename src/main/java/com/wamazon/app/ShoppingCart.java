package com.wamazon.app;
import com.wamazon.app.Model.BaseProductModel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShoppingCart {
	private static ShoppingCart instance;
    private Map<UUID, BaseProductModel> items;
    private int itemCount;

    public ShoppingCart() {
        this.items = new HashMap<>();
        this.itemCount = 0;
    }
    
    public static synchronized ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void setItems(Map<UUID, BaseProductModel> items) {
        this.items = items;
        this.itemCount= items.size();
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public Map<UUID, BaseProductModel> getItems() {
        return items;
    }

    public int getItemCount() {
        return itemCount;
    }
}
