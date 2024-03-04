package com.wamazon.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	private final UserRepository userRepository;
	
	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/")
    public String HomePageRoute() {
		//Return home template
      return "welcome";
    }
	
	@GetMapping("/register")
	public String RegistrationRoute() {
		//Return register template
		return "register";
	}
	
	@PostMapping("/register-post")
    public String registerUser(@RequestParam(name = "username", defaultValue = "defaultUser") String username,
                               @RequestParam(name = "password", defaultValue = "defaultPassword") String password) {
		UserModel newUser = new UserModel(username, password);
        userRepository.save(newUser);

        // Redirect to a success page after registration
        return "redirect:/success.html";
    }

}
