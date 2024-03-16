package com.wamazon.app.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wamazon.app.DataSeeder;
import com.wamazon.app.LogEntryService;
import com.wamazon.app.PaymentForm;
import com.wamazon.app.ShoppingCart;
import com.wamazon.app.ShoppingCartBuilder;
import com.wamazon.app.Model.BaseProductModel;
import com.wamazon.app.Model.BaseProductRepository;
import com.wamazon.app.Model.UserModel;
import com.wamazon.app.Model.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PaymentController {
	private final ShoppingCartBuilder cartBuilder;
    private final UserRepository userRepository;
	@Autowired
	private LogEntryService logEntryService;
	
    @Autowired
    public PaymentController(ShoppingCartBuilder cartBuilder, UserRepository userRepository) {
        this.cartBuilder = cartBuilder;
        this.userRepository = userRepository;
    }
    
    @GetMapping("/payment")
    public String showPaymentForm(Model model) {
        model.addAttribute("paymentForm", new PaymentForm());
        return "payment";
    }
    
    @PostMapping("/process-payment")
    public String processPayment(@Valid PaymentForm paymentForm, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "payment";
        }
        ShoppingCart cart = cartBuilder.build();
        Double price = CartController.calculateTotalSum(cart.getItems());
        UserModel user = userRepository.getReferenceById((Long) session.getAttribute("userid"));
        this.logEntryService.logEvent(user.getUsername()+" made a payment of "+price, user);
        cart.setItemCount(0);
        cart.setItems(new HashMap<UUID, BaseProductModel>());
        

        return "confirmation";
    }
}
