package am.martirosyan.dormru.controller;

import am.martirosyan.dormru.dto.NewsResponse;
import am.martirosyan.dormru.service.api.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NewsService newsService;

    // Главная страница с новостями
    @GetMapping("/home")
    public String home(Model model) {
        List<NewsResponse> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);
        return "index";
    }

    // Страница конкретной новости
    @GetMapping("/news/{id}")
    public String viewNews(@PathVariable Long id, Model model) {
        NewsResponse news = newsService.getById(id);
        model.addAttribute("news", news);
        return "news";
    }
}
