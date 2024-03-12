package com.wamazon.app.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wamazon.app.Model.BaseProductFactory;
import com.wamazon.app.Model.BaseProductModel;
import com.wamazon.app.Model.BaseProductRepository;
import com.wamazon.app.DataSeeder;
import com.wamazon.app.ShoppingCart;

import org.springframework.ui.Model;

@Controller
public class ItemController {
	private final BaseProductRepository productRepo;
	 private final DataSeeder dataSeeder;
	 private final ShoppingCart shoppingCart;
	
	@Autowired
	public ItemController(BaseProductRepository productRepo, DataSeeder dataSeeder) {
		this.productRepo = productRepo;
		this.dataSeeder = dataSeeder;
		this.shoppingCart = ShoppingCart.getInstance();
	}
	
	@PostMapping("/seeder")
	public String seedData() {
	        dataSeeder.seedData();
	        return "Data Seeding Triggered!";
	    }

	@GetMapping("/item-form")
	public String ItemFormRoute() {
		return "item-form";
	}
	
	@Transactional
	@PostMapping("/add-to-cart/{productId}")
	public String addToCart(@PathVariable(value="productId") Long productId) {
        // Assuming productId is of type Long
		Optional<BaseProductModel> addedProductModel = this.productRepo.findById(productId);
        this.shoppingCart.addItem(addedProductModel.get());
        return "portal";
    }
	
	@PostMapping("/add-item")
	public String AddItemFormRoute(@RequestParam(name = "productName", defaultValue = "") String productname,
			@RequestParam(name = "price") double price,
			@RequestParam(name="description") String description, Model model) {
		if(productname.equals("")) {
			model.addAttribute("error", "You can't leave the product name empty!");
			return "/item-form";
		}
		else if(price <= 0.0) {
			model.addAttribute("error", "The price needs to be a positive integer");
			return "/item-form";
		}else {
			BaseProductFactory factory = new BaseProductFactory();
			BaseProductModel newProductModel =factory.createProduct("base", productname, price, description);
			this.productRepo.save(newProductModel);
			return "redirect:/portal";
		}
		
	}

}
