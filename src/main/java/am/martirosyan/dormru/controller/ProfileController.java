package am.martirosyan.dormru.controller;

import am.martirosyan.dormru.dto.response.UserResponse;
import am.martirosyan.dormru.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    @GetMapping
    public String profilePage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        UserResponse user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/image")
    public String uploadProfileImage(@AuthenticationPrincipal UserDetails userDetails,
                                     MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String uploadDir = new ClassPathResource("static/images/").getFile().getAbsolutePath();

            String filename = userDetails.getUsername() + "_" + image.getOriginalFilename();

            Path dest = Path.of(uploadDir, filename);
            Files.copy(image.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);

            String relativePath = "/images/" + filename;
            userService.updateProfileImage(userDetails.getUsername(), relativePath);
        }

        return "redirect:/profile";
    }

    @PostMapping("/image/delete")
    public String deleteProfileImage(@AuthenticationPrincipal UserDetails userDetails) {
        userService.updateProfileImage(userDetails.getUsername(), "/images/default-avatar.png");
        return "redirect:/profile";
    }

}
