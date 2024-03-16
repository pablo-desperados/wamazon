package com.wamazon.app.Controller;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wamazon.app.LogEntryService;
import com.wamazon.app.ShoppingCart;
import com.wamazon.app.ShoppingCartBuilder;
import com.wamazon.app.Model.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	private final UserRepository userRepository;
	private final ShoppingCartBuilder shoppingCart;
	@Autowired
	private LogEntryService logEntryService;

	
	@Autowired
	public CartController(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.shoppingCart = new ShoppingCartBuilder();
	}
	
	@GetMapping("/cart")
	public String CartRoute(Model model, HttpSession session) {
		Map<UUID, BaseProductModel> cartItems = this.shoppingCart.build().getItems();
		UserModel user = userRepository.getReferenceById((Long) session.getAttribute("userid"));
		double totalSum = calculateTotalSum(cartItems);
		model.addAttribute("cartItems",cartItems);
		model.addAttribute("totalSum",totalSum);
		model.addAttribute("currUsername", user.getUsername());
		
		return "cart";
	}
	
	@PostMapping("/remove-item")
	public String DeleteCart(@RequestParam(name = "uuid") UUID id, HttpSession session) {
		UserModel user = userRepository.getReferenceById((Long) session.getAttribute("userid"));
		this.logEntryService.logEvent("Item "+this.shoppingCart.build().getItems().get(id).getName()+ "was removed from the cart", user);
		this.shoppingCart.removeItem(id);
		return "redirect:/cart";
		
	}
	
	 public static double calculateTotalSum(Map<UUID ,BaseProductModel> cartItems) {
	        // Dummy implementation, you should replace this with your actual logic
	        double totalSum = 0.0;
	        for (BaseProductModel item : cartItems.values()) {
	            totalSum += item.getPrice();
	        }
	        return totalSum;
	    }

}
