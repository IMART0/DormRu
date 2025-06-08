package am.martirosyan.dormru.controller;

import am.martirosyan.dormru.dto.EventResponse;
import am.martirosyan.dormru.service.api.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public String showEvents(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("eventDate").ascending());
        Page<EventResponse> eventPage = eventService.searchEvents(keyword, date, pageable);

        model.addAttribute("events", eventPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("date", date);

        return "events";
    }


    @GetMapping("/{id}")
    public String getEventDetails(@PathVariable Long id, Model model) {
        EventResponse event = eventService.getById(id);
        model.addAttribute("event", event);
        return "event-details"; // Название шаблона
    }


}
