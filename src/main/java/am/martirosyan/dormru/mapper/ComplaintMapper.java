package am.martirosyan.dormru.mapper;

import am.martirosyan.dormru.dto.request.ComplaintRequest;
import am.martirosyan.dormru.dto.response.ComplaintResponse;
import am.martirosyan.dormru.model.Complaint;
import am.martirosyan.dormru.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComplaintMapper implements Mapper<Complaint, ComplaintRequest, ComplaintResponse> {

    private final UserMapper userMapper;

    @Override
    public Complaint toEntity(ComplaintRequest complaintRequest) {
        return Complaint.builder()
                .complaintText(complaintRequest.getComplaintText())
                .status(complaintRequest.getStatus())
                .user(User.builder().id(complaintRequest.getUserId()).build())
                .build();
    }

    @Override
    public ComplaintResponse toDto(Complaint complaint) {
        return ComplaintResponse.builder()
                .id(complaint.getId())
                .user(userMapper.toDto(complaint.getUser()))
                .complaintText(complaint.getComplaintText())
                .status(complaint.getStatus())
                .build();
    }
}
