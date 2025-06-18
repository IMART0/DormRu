package am.martirosyan.dormru.controller;

import am.martirosyan.dormru.dto.response.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RestTemplate restTemplate;

    private final String newsServiceUrl = "http://localhost:8081/api/news";

    @GetMapping("/home")
    public String home(Model model) {
        ResponseEntity<NewsResponse[]> response = restTemplate.getForEntity(newsServiceUrl, NewsResponse[].class);
        List<NewsResponse> newsList = Arrays.asList(response.getBody());
        model.addAttribute("newsList", newsList);
        return "index";
    }

    @GetMapping("/news/{id}")
    public String viewNews(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes, Model model) {
        if (principal == null) {
            redirectAttributes.addFlashAttribute("warning", "Для просмотра новости необходимо авторизоваться.");
            return "redirect:/home";
        }
        String url = newsServiceUrl + "/" + id;
        ResponseEntity<NewsResponse> response = restTemplate.getForEntity(url, NewsResponse.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            redirectAttributes.addFlashAttribute("warning", "Новость не найдена");
            return "redirect:/home";
        }
        model.addAttribute("news", response.getBody());
        return "news";
    }
}
