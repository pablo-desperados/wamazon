package com.wamazon.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/")
	public String HomePageRoute() {
		// Return home template
		return "welcome";
	}

	@GetMapping("/portal")
	public String PortalRoute(HttpSession session, Model model) {

		UserModel user = userRepository.getReferenceById((Long) session.getAttribute("userid"));
		model.addAttribute("currUsername", user.getUsername());
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
			HttpServletRequest request) {
			
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
