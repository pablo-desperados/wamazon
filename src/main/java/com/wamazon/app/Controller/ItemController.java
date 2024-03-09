package com.wamazon.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wamazon.app.Model.BaseProductRepository;
import com.wamazon.app.Model.UserRepository;

import org.springframework.ui.Model;

@Controller
public class ItemController {
	private final BaseProductRepository productRepo;
	
	@Autowired
	public ItemController(BaseProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@GetMapping("/item-form")
	public String ItemFormRoute() {
		return "item-form";
	}

	@PostMapping("/add-item")
	public String AddItemFormRoute(@RequestParam(name = "productName", defaultValue = "") String productname,
			@RequestParam(name = "price") double price,
			@RequestParam(name="description") String description, Model model) {
		if(productname.equals("")) {
			model.addAttribute("error", "You can't leave the product nme empty!");
			return "item-form";
		}
		else if(price <= 0.0) {
			model.addAttribute("error", "The price needs to be a positive integer");
			return "item-form";
		}else {
			
			return "portal";
		}
		
	}

}
