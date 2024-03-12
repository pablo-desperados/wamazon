package com.wamazon.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wamazon.app.Model.BaseProductRepository;
import com.wamazon.app.Model.UserModel;
import com.wamazon.app.ShoppingCart;
import com.wamazon.app.Model.BaseProductModel;
import com.wamazon.app.Model.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	private final UserRepository userRepository;
	private final BaseProductRepository baseProductRepository;
	private final ShoppingCart shoppingCart;

	public UserModel findByUsernameAndPassword(String username, String password) {
		UserModel user = userRepository.findByUsernameAndPassword(username, password);
		if (user == null) {
			throw new RuntimeException("Invalid username or password");
		}
		return user;
	}

	@Autowired
	public UserController(UserRepository userRepository, BaseProductRepository baseProductRepository) {
		this.userRepository = userRepository;
		this.baseProductRepository = baseProductRepository;
		this.shoppingCart = ShoppingCart.getInstance();
	}

	@GetMapping("/")
	public String HomePageRoute() {
		// Return home template
		return "welcome";
	}

	@GetMapping("/portal")
	public String PortalRoute(HttpSession session, Model model) {
		
        List<BaseProductModel> products = baseProductRepository.findAll();
		UserModel user = userRepository.getReferenceById((Long) session.getAttribute("userid"));
		model.addAttribute("currUsername", user.getUsername());
		model.addAttribute("products", products);
		model.addAttribute("cartCounter",this.shoppingCart.getItemCount());
		return "portal";
	}

	@GetMapping("/register")
	public String RegistrationRoute() {
		// Return register template
		return "register";
	}

	@GetMapping("/login")
	public String LoginRoute() {
		// Return register template
		return "login";
	}

	@PostMapping("/login-post")
	public String LoginUser(@RequestParam(name = "username", defaultValue = "defaultUser") String username,
			@RequestParam(name = "password", defaultValue = "defaultPassword") String password,
			HttpServletRequest request,
			Model model) {

		try {
			UserModel user = findByUsernameAndPassword(username, password);
			HttpSession session = request.getSession();
			session.setAttribute("userid", user.getId());
			return "redirect:/portal"; // Replace with your success template
		} catch (RuntimeException e) {
			model.addAttribute("error", "username/password are incorrect. Please try again!");
			return "login"; // Replace with your login template
		}

	}

	@PostMapping("/register-post")
	public String registerUser(@RequestParam(name = "username", defaultValue = "defaultUser") String username,
			@RequestParam(name = "password", defaultValue = "defaultPassword") String password,
			HttpServletRequest request) {

		UserModel newUser = new UserModel(username, password);
		userRepository.save(newUser);
		HttpSession session = request.getSession();
		session.setAttribute("userid", newUser.getId());

		// Redirect to the main page after registration
		return "redirect:/portal";
	}

}
