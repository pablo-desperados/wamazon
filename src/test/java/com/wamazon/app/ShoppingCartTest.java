package com.wamazon.app;

import com.wamazon.app.Model.BaseProductFactory;
import com.wamazon.app.Model.BaseProductModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ShoppingCartTest {

    private ShoppingCart shoppingCart;

    @BeforeEach
    public void setUp() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void testGetItems() {
        Map<UUID, BaseProductModel> items = shoppingCart.getItems();
        assertNotNull(items);
        assertEquals(0, items.size());
    }

    @Test
    public void testSetItems() {
    	BaseProductFactory factory = new BaseProductFactory();
        Map<UUID, BaseProductModel> items = new HashMap<>();
        BaseProductModel product1 = factory.createProduct("Product 1", null, 0, null, null);
        BaseProductModel product2 = factory.createProduct("Product 2", null, 0, null, null);
        UUID one = UUID.randomUUID();
        UUID two = UUID.randomUUID();
        items.put(one, product1);
        items.put(two, product2);

        shoppingCart.setItems(items);

        Map<UUID, BaseProductModel> retrievedItems = shoppingCart.getItems();
        assertNotNull(retrievedItems);
        assertEquals(2, retrievedItems.size());
        
        assertSame(product1, retrievedItems.get(one));
        assertSame(product2, retrievedItems.get(two));
    }

    @Test
    public void testGetItemCount() {
        assertEquals(0, shoppingCart.getItemCount());
        BaseProductFactory factory = new BaseProductFactory();
        Map<UUID, BaseProductModel> items = new HashMap<>();
        items.put(UUID.randomUUID(), factory.createProduct("Product 1", null, 0, null, null));
        items.put(UUID.randomUUID(), factory.createProduct("Product 2", null, 0, null, null));
        shoppingCart.setItems(items);

        assertEquals(2, shoppingCart.getItemCount());
    }

    @Test
    public void testSingletonPattern() {
        ShoppingCart cart1 = ShoppingCart.getInstance();
        ShoppingCart cart2 = ShoppingCart.getInstance();

        assertNotNull(cart1);
        assertNotNull(cart2);
        assertSame(cart1, cart2);
    }
}
