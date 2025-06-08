package am.martirosyan.dormru.controller;

import am.martirosyan.dormru.dto.EventResponse;
import am.martirosyan.dormru.service.api.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public String showEvents(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {

        List<EventResponse> events = eventService.searchEvents(keyword, date);
        model.addAttribute("events", events);
        model.addAttribute("keyword", keyword);
        model.addAttribute("date", date);

        return "events";
    }

}
