package am.martirosyan.dormru.controller;

import am.martirosyan.dormru.dto.EventResponse;
import am.martirosyan.dormru.dto.UserResponse;
import am.martirosyan.dormru.service.api.EventService;
import am.martirosyan.dormru.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final UserService userService;

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
    public String getEventDetails(
            @PathVariable Long id,
            Model model,
            @RequestParam(value = "success", required = false) Boolean success,
            @RequestParam(value = "already", required = false) Boolean already
    ) {
        EventResponse event = eventService.getById(id);
        model.addAttribute("event", event);
        model.addAttribute("notRegistered", !Boolean.TRUE.equals(success) && !Boolean.TRUE.equals(already));
        model.addAttribute("registrationSuccess", success != null && success);
        model.addAttribute("alreadyRegistered", already != null && already);
        return "event-details";
    }

    @PostMapping("/{id}/register")
    public String registerForEvent(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            RedirectAttributes redirectAttributes
    ) {
        UserResponse user = userService.getUserByEmail(userDetails.getUsername());
        boolean already = eventService.isUserAlreadyRegistered(user.getId(), id);
        if (already) {
            redirectAttributes.addAttribute("already", true);
        } else {
            eventService.registerUserForEvent(user.getId(), id);
            redirectAttributes.addAttribute("success", true);
        }
        return "redirect:/events/{id}";
    }
}
