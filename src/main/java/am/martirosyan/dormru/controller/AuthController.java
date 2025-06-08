package am.martirosyan.dormru.controller;

import am.martirosyan.dormru.dto.UserRequest;
import am.martirosyan.dormru.service.api.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new UserRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserRequest dto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            model.addAttribute("errors", errors);
            return "register";
        }

        userService.register(dto);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/home";
    }
}
