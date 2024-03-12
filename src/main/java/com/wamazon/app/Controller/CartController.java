package com.wamazon.app.Controller;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wamazon.app.ShoppingCart;
import com.wamazon.app.Model.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	private final UserRepository userRepository;
	private final ShoppingCart shoppingCart;
	
	@Autowired
	public CartController(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.shoppingCart = ShoppingCart.getInstance();
	}
	
	@GetMapping("/cart")
	public String CartRoute(Model model, HttpSession session) {
		Map<UUID, BaseProductModel> cartItems = this.shoppingCart.getItems();
		UserModel user = userRepository.getReferenceById((Long) session.getAttribute("userid"));
		double totalSum = calculateTotalSum(cartItems);
		model.addAttribute("cartItems",cartItems);
		model.addAttribute("totalSum",totalSum);
		model.addAttribute("currUsername", user.getUsername());
		
		return "cart";
	}
	
	@PostMapping("/remove-item")
	public String DeleteCart(@RequestParam(name = "uuid") UUID id) {
		this.shoppingCart.removeItem(id);
		return "redirect:/cart";
		
	}
	
	 private double calculateTotalSum(Map<UUID ,BaseProductModel> cartItems) {
	        // Dummy implementation, you should replace this with your actual logic
	        double totalSum = 0.0;
	        for (BaseProductModel item : cartItems.values()) {
	            totalSum += item.getPrice();
	        }
	        return totalSum;
	    }

}