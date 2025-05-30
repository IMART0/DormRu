package am.martirosyan.dormru.controller;

import am.martirosyan.dormru.dto.UserDto;
import am.martirosyan.dormru.service.api.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserDto dto) {
        userService.register(dto);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
