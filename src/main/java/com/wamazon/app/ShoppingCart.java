package com.wamazon.app;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static ShoppingCart instance;
    private List<String> items;
    private int itemCount;

    private ShoppingCart() {
        this.items = new ArrayList<>();
        this.itemCount = 0;
    }

    public static synchronized ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addItem(String item) {
        items.add(item);
        itemCount++;
    }

    public void removeItem(String item) {
        items.remove(item);
        itemCount--;
    }

    public List<String> getItems() {
        return items;
    }

    public int getItemCount() {
        return itemCount;
    }
}
