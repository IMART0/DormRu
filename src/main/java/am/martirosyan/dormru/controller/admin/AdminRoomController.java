package am.martirosyan.dormru.controller.admin;

import am.martirosyan.dormru.dto.response.RoomResponse;
import am.martirosyan.dormru.dto.response.RoomResponseWithResidents;
import am.martirosyan.dormru.service.api.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin/rooms")
public class AdminRoomController {

    private final RoomService roomService;

    @GetMapping
    public String showRooms(@RequestParam(required = false) String keyword, Model model) {
        List<RoomResponse> rooms = roomService.searchRooms(keyword);
        model.addAttribute("rooms", rooms);
        model.addAttribute("keyword", keyword);
        return "admin/rooms";
    }

    @GetMapping("/{id}")
    public String showRoomDetails(@PathVariable Integer id, Model model) {
        RoomResponseWithResidents room = roomService.getRoomWithResidents(id);
        model.addAttribute("room", room);
        return "admin/room-details";
    }
}

