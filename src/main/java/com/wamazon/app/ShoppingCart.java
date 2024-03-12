package com.wamazon.app;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.wamazon.app.Model.BaseProductModel;

public class ShoppingCart {
    private static ShoppingCart instance;
    private Map<UUID,BaseProductModel> items;
    private int itemCount;

    private ShoppingCart() {
        this.items = new HashMap<UUID,BaseProductModel>();
        this.itemCount = 0;
    }

    public static synchronized ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addItem(BaseProductModel addedProductModel) {
    	if(addedProductModel != null) {
    		UUID uuid = UUID.randomUUID();
    		items.put(uuid, addedProductModel);
    		itemCount++;
    	}
 
    }

    public void removeItem(UUID id) {
        items.remove(id);
        itemCount--;
    }

    public Map<UUID, BaseProductModel> getItems() {
        return items;
    }

    public int getItemCount() {
        return itemCount;
    }
}
