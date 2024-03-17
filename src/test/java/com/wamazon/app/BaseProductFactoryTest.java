package com.wamazon.app;

import org.junit.jupiter.api.Test;


import com.wamazon.app.Model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseProductFactoryTest {

    private final BaseProductFactory factory = new BaseProductFactory();

    @Test
    public void testCreateStereoProduct() {
        // Arrange
        String type = "stereo";

        // Act
        BaseProductModel product = factory.createProduct(type, null, 0, null, null);

        // Assert
        assertTrue(product instanceof StereoProductModel);
        assertEquals("Wamazon Stereo", product.getName());
        assertEquals(20, product.getPrice());
    }

    @Test
    public void testCreateGamingPCProduct() {
        // Arrange
        String type = "pc";

        // Act
        BaseProductModel product = factory.createProduct(type, null, 0, null, null);

        // Assert
        assertTrue(product instanceof GamingPCProductModel);
        assertEquals("Wamazon Gaming PC", product.getName());
        assertEquals(600, product.getPrice());
    }

    @Test
    public void testCreatePlasmaTVProduct() {
        // Arrange
        String type = "tv";

        // Act
        BaseProductModel product = factory.createProduct(type, null, 0, null, null);

        // Assert
        assertTrue(product instanceof PlasmaTvModel);
        assertEquals("Wamazon 80-inch TV", product.getName());
        assertEquals(900, product.getPrice());
    }

    @Test
    public void testCreateTabletProduct() {
        // Arrange
        String type = "tablet";

        // Act
        BaseProductModel product = factory.createProduct(type, null, 0, null, null);

        // Assert
        assertTrue(product instanceof TabletProductModel);
        assertEquals("Wamazon Android table", product.getName());
        assertEquals(900, product.getPrice());
    }

    @Test
    public void testCreateDefaultProduct() {
        // Arrange
        String type = "unknown";
        String name = "Default Product";
        double price = 50.0;

        // Act
        BaseProductModel product = factory.createProduct(type, name, price, null, null);

        // Assert
        assertTrue(product instanceof BaseProductModel);
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
    }
}
