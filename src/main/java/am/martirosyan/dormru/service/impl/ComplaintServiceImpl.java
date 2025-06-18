package am.martirosyan.dormru.service.impl;

import am.martirosyan.dormru.dto.request.ComplaintRequest;
import am.martirosyan.dormru.dto.response.ComplaintResponse;
import am.martirosyan.dormru.mapper.ComplaintMapper;
import am.martirosyan.dormru.model.Complaint;
import am.martirosyan.dormru.model.ComplaintStatus;
import am.martirosyan.dormru.model.User;
import am.martirosyan.dormru.repository.ComplaintRepository;
import am.martirosyan.dormru.repository.UserRepository;
import am.martirosyan.dormru.service.EmailService;
import am.martirosyan.dormru.service.api.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    private final ComplaintMapper complaintMapper;

    private final EmailService emailService;

    @Override
    public List<ComplaintResponse> getComplaintsByUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return complaintRepository.findByUser(user)
                .stream()
                .map(complaintMapper::toDto)
                .toList();
    }

    @Override
    public void createComplaint(String email, ComplaintRequest complaintRequest) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        var complaint = complaintMapper.toEntity(complaintRequest);
        complaint.setUser(user);
        complaint.setStatus(ComplaintStatus.CREATED);
        complaint.setCreatedDate(LocalDateTime.now());
        complaintRepository.save(complaint);
    }

    public List<ComplaintResponse> getComplaintsFiltered(ComplaintStatus status) {
        return (status == null)
                ? complaintRepository.findAll().stream().map(complaintMapper::toDto).toList()
                : complaintRepository.findByStatus((status)).stream().map(complaintMapper::toDto).toList();
    }

    public ComplaintResponse getComplaintById(Long id) {
        return complaintRepository.findById(id).map(complaintMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Жалоба не найдена"));
    }

    public void updateComplaintStatus(Long id, ComplaintStatus newStatus) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Жалоба не найдена"));
        complaint.setStatus(newStatus);
        complaintRepository.save(complaint);
        emailService.sendStatusUpdateEmail(complaint.getUser().getEmail(),
                complaint.getStatus().toString(), complaint.getComplaintText());
    }


}
