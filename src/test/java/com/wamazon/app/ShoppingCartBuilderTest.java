package com.wamazon.app;

import com.wamazon.app.Model.BaseProductModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShoppingCartBuilderTest {

    private ShoppingCartBuilder cartBuilder;
    private BaseProductModel product1;
    private BaseProductModel product2;
    
    @BeforeEach
    public void setUp() {
        cartBuilder = new ShoppingCartBuilder();
        product1 = new BaseProductModel();
        product2 = new BaseProductModel();
    }

    @Test
    public void testAddItem() {
        ShoppingCart cart = cartBuilder
                .addItem(product1)
                .addItem(product2)
                .build();

        assertNotNull(cart);
        assertNotNull(cart.getItems());
        assertEquals(2, cart.getItems().size());
        assertEquals(2, cart.getItemCount());
    }

    @Test
    public void testRemoveItem() {
        
        
        ShoppingCart cart = cartBuilder
                .build();
        
        cart.setItems(new HashMap<UUID, BaseProductModel>());
        
        
        cart = cartBuilder
                .addItem(product1)
                .addItem(product2)
                .build();
        
        assertNotNull(cart);
        assertNotNull(cart.getItems());
        assertEquals(2, cart.getItems().size());
        assertEquals(2, cart.getItemCount());
        
        Object[] list = cartBuilder.build().getItems().keySet().toArray();
        for (Object id : list) {
        	cartBuilder.removeItem((UUID) id);     
		}      

        assertEquals(0, cart.getItems().size());
        assertEquals(0, cart.getItemCount());
    }

    @Test
    public void testBuildWithNullItem() {
        ShoppingCart cart = cartBuilder
                .addItem(product1)
                .addItem(null)
                .addItem(product2)
                .build();

        assertNotNull(cart);
        assertNotNull(cart.getItems());
        assertEquals(4, cart.getItems().size());
        assertEquals(4, cart.getItemCount());
    }
}
