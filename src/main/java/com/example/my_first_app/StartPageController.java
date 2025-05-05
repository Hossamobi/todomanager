package com.example.my_first_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class StartPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password) {
        var user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setSecret(UUID.randomUUID().toString());
        var saved = userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        var user = userRepository.findByEmailAndPassword(email, password);
        if (user.isPresent()) {
            int userId = user.get().getId();
            return "redirect:/todos-page?userId=" + userId;
        }
        model.addAttribute("error", "Login fehlgeschlagen");
        return "login";
    }
}
