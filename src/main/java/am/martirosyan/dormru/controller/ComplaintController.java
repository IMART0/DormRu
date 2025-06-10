package am.martirosyan.dormru.controller;

import am.martirosyan.dormru.dto.response.ComplaintResponse;
import am.martirosyan.dormru.service.api.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @GetMapping("/complaints")
    public String userComplaints(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String email = userDetails.getUsername();
        List<ComplaintResponse> complaints = complaintService.getComplaintsByUser(email);
        model.addAttribute("complaints", complaints);
        return "complaints";
    }

    @GetMapping("/complaints/new")
    public String newComplaintForm(Model model) {
        model.addAttribute("complaint", new ComplaintResponse());
        return "complaint_form";
    }
}
