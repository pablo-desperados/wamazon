package com.wamazon.app.Controller;

import com.wamazon.app.ShoppingCartBuilder;
import com.wamazon.app.Model.BaseProductModel;
import com.wamazon.app.Model.BaseProductRepository;
import com.wamazon.app.Model.UserModel;
import com.wamazon.app.Model.UserRepository;
import com.wamazon.app.LogEntryService;
import com.wamazon.app.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final BaseProductRepository baseProductRepository;
	@Autowired
	private LogEntryService logEntryService;

    @Autowired
    public UserController(UserRepository userRepository, BaseProductRepository baseProductRepository) {
        this.userRepository = userRepository;
        this.baseProductRepository = baseProductRepository;
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

        // Get the shopping cart items and count
        ShoppingCartBuilder cartBuilder = new ShoppingCartBuilder();
        ShoppingCart cart = cartBuilder.build();
        Map<UUID, BaseProductModel> cartItems = cart.getItems();
        int cartCounter = cart.getItemCount();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartCounter", cartCounter);

        return "portal";
    }

    @GetMapping("/register")
    public String RegistrationRoute() {
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
            this.logEntryService.logEvent(user.getUsername()+" has logged in", user);
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
        this.logEntryService.logEvent(newUser.getUsername()+" has registered", newUser);
        // Redirect to the main page after registration
        return "redirect:/portal";
    }

    private UserModel findByUsernameAndPassword(String username, String password) {
        UserModel user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new RuntimeException("Invalid username or password");
        }
        return user;
    }

}
