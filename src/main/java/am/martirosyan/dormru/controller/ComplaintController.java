package am.martirosyan.dormru.controller;

import am.martirosyan.dormru.dto.request.ComplaintRequest;
import am.martirosyan.dormru.dto.response.ComplaintResponse;
import am.martirosyan.dormru.service.api.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    @GetMapping
    public String userComplaints(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        List<ComplaintResponse> complaints = complaintService.getComplaintsByUser(email);
        model.addAttribute("complaints", complaints);
        return "complaints";
    }

    @GetMapping("/new")
    public String newComplaintForm(Model model) {
        model.addAttribute("complaint", new ComplaintResponse());
        return "complaint_form";
    }

    @PostMapping
    public String submitComplaint(@ModelAttribute ComplaintRequest complaintRequest,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        complaintService.createComplaint(email, complaintRequest);
        return "redirect:/complaints";
    }
}
