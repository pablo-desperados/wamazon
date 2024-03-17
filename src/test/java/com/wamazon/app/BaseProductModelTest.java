package com.wamazon.app;

import org.junit.jupiter.api.Test;

import com.wamazon.app.Model.BaseProductModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BaseProductModelTest {

    @Test
    public void testConstructorWithAllFields() {
        String name = "Test Product";
        double price = 100.0;
        String description = "This is a test product";
        String image = "test_image.jpg";

        BaseProductModel product = new BaseProductModel(name, price, description, image);

        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(description, product.getDescription());
        assertEquals(image, product.getImage());
        assertNull(product.getId());
    }

    @Test
    public void testConstructorWithRequiredFields() {
        String name = "Test Product";
        double price = 100.0;

        BaseProductModel product = new BaseProductModel(name, price);

        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertNull(product.getDescription());
        assertNull(product.getImage());
        assertNull(product.getId());
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        String name = "Test Product";
        double price = 100.0;
        String description = "This is a test product";
        String image = "test_image.jpg";

        BaseProductModel product = new BaseProductModel();

        product.setImage(image);

        assertEquals(image, product.getImage());
    }
}
