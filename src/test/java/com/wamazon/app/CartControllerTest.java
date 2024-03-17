package com.wamazon.app;

import com.wamazon.app.Model.BaseProductModel;
import com.wamazon.app.Model.UserModel;
import com.wamazon.app.Model.UserRepository;
import com.wamazon.app.ShoppingCartBuilder;
import com.wamazon.app.Controller.CartController;
import com.wamazon.app.LogEntryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LogEntryService logEntryService;

    @Test
    public void testCalculateTotalSum() {
        // Arrange
        CartController cartController = new CartController(userRepository);
        MockitoAnnotations.openMocks(this);

        Map<UUID, BaseProductModel> cartItems = new HashMap<>();
        cartItems.put(UUID.randomUUID(), new BaseProductModel("Product 1", 10.0));
        cartItems.put(UUID.randomUUID(), new BaseProductModel("Product 2", 20.0));
        cartItems.put(UUID.randomUUID(), new BaseProductModel("Product 3", 30.0));

        // Act
        double totalSum = cartController.calculateTotalSum(cartItems);

        // Assert
        assertEquals(60.0, totalSum, 0.001); // We use delta to account for floating point precision
    }
}
