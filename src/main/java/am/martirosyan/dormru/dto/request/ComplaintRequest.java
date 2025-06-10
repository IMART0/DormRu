package am.martirosyan.dormru.dto.request;

import am.martirosyan.dormru.model.ComplaintStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComplaintRequest {
    private Long userId;
    private String complaintText;
    private ComplaintStatus status;
}
