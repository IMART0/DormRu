package am.martirosyan.dormru.controller.admin;

import am.martirosyan.dormru.dto.response.ComplaintResponse;
import am.martirosyan.dormru.model.ComplaintStatus;
import am.martirosyan.dormru.service.api.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin/complaints")
public class AdminComplaintController {

    private final ComplaintService complaintService;

    @GetMapping
    public String listComplaints(@RequestParam(required = false) String status, Model model) {

        ComplaintStatus complaintStatus = null;
        if (status != null)
            complaintStatus = ComplaintStatus.toStatus(status);

        List<ComplaintResponse> complaints = complaintService.getComplaintsFiltered(complaintStatus);
        model.addAttribute("complaints", complaints);
        model.addAttribute("statuses", ComplaintStatus.values());
        model.addAttribute("selectedStatus", status);
        return "admin/complaints";
    }

    @GetMapping("/{id}")
    public String viewComplaint(@PathVariable Long id, Model model) {
        ComplaintResponse complaint = complaintService.getComplaintById(id);
        model.addAttribute("complaint", complaint);
        model.addAttribute("statuses", ComplaintStatus.values());
        return "admin/complaint-detail";
    }

    @PostMapping("/{id}/update")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        ComplaintStatus complaintStatus = ComplaintStatus.toStatus(status);
        complaintService.updateComplaintStatus(id, complaintStatus);
        return "redirect:/admin/complaints/" + id;
    }
}
