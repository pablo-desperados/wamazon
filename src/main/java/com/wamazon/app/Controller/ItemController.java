package com.wamazon.app.Controller;

import java.util.Optional;

import org.apache.commons.validator.routines.UrlValidator;
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
import com.wamazon.app.Model.UserModel;
import com.wamazon.app.Model.UserRepository;

import jakarta.servlet.http.HttpSession;

import com.wamazon.app.DataSeeder;
import com.wamazon.app.LogEntryService;
import com.wamazon.app.ShoppingCartBuilder;

import org.springframework.ui.Model;


@Controller
public class ItemController {
    private final BaseProductRepository productRepo;
    private final DataSeeder dataSeeder;
    private final ShoppingCartBuilder cartBuilder;
    private final UserRepository userRepository;
	@Autowired
	private LogEntryService logEntryService;

    @Autowired
    public ItemController(BaseProductRepository productRepo, DataSeeder dataSeeder, ShoppingCartBuilder cartBuilder, UserRepository userRepository ) {
        this.productRepo = productRepo;
        this.dataSeeder = dataSeeder;
        this.cartBuilder = cartBuilder;
        this.userRepository = userRepository;
    }

    @PostMapping("/seeder")
    public void seedData() {
        dataSeeder.seedData();
    }

    @GetMapping("/item-form")
    public String ItemFormRoute() {
        return "item-form";
    }

    @Transactional
    @PostMapping("/add-to-cart/{productId}")
    public String addToCart(@PathVariable(value = "productId") Long productId, HttpSession session) {
    	UserModel user = userRepository.getReferenceById((Long) session.getAttribute("userid"));
        Optional<BaseProductModel> addedProductModel = this.productRepo.findById(productId);
        if (addedProductModel.isPresent()) {
        	this.logEntryService.logEvent("Item "+addedProductModel.get().getName()+" was added to the cart", user);
            cartBuilder.addItem(addedProductModel.get());
            
        }
        return "redirect:/portal";
    }

    @PostMapping("/add-item")
    public String AddItemFormRoute(@RequestParam(name = "productName", defaultValue = "") String productname,
            @RequestParam(name = "price") double price,
            @RequestParam(name = "description") String description, Model model,
            @RequestParam(name="image", defaultValue = "") String image,
            HttpSession session) {
    	UrlValidator validator = new UrlValidator();
    	UserModel user = userRepository.getReferenceById((Long) session.getAttribute("userid"));
        if (productname.equals("")) {
            model.addAttribute("error", "You can't leave the product name empty!");
            return "/item-form";
        } else if (price <= 0.0) {
            model.addAttribute("error", "The price needs to be a positive integer");
            return "/item-form";
        } else if(!validator.isValid(image)){
        	model.addAttribute("error", "The image needs to be a valid url");
        	return "/item-form";
        }else {
            BaseProductFactory factory = new BaseProductFactory();
            BaseProductModel newProductModel = factory.createProduct("base", productname, price, description,image);
            productRepo.save(newProductModel);
            cartBuilder.addItem(newProductModel); // Adding the new product to cart
            this.logEntryService.logEvent("Product "+newProductModel.getName()+" was added to the portal", user);
            return "redirect:/portal";
        }
    }
}