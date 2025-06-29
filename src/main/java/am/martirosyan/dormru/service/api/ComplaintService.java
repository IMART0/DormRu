package am.martirosyan.dormru.service.api;

import am.martirosyan.dormru.dto.request.ComplaintRequest;
import am.martirosyan.dormru.dto.response.ComplaintResponse;
import am.martirosyan.dormru.model.ComplaintStatus;

import java.util.List;

public interface ComplaintService {
    List<ComplaintResponse> getComplaintsByUser(String email);

    void createComplaint(String email, ComplaintRequest complaintRequest);

    List<ComplaintResponse> getComplaintsFiltered(ComplaintStatus status);

    ComplaintResponse getComplaintById(Long id);

    void updateComplaintStatus(Long id, ComplaintStatus status);
}
