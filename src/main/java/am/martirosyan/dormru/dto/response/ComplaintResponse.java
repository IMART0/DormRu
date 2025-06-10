package am.martirosyan.dormru.dto.response;

import am.martirosyan.dormru.model.ComplaintStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplaintResponse {
    private Long id;
    private UserResponse user;
    private String complaintText;
    private ComplaintStatus status;
}
