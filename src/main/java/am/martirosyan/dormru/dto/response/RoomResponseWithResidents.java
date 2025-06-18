package am.martirosyan.dormru.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponseWithResidents {
    private Integer roomNumber;
    private Integer capacity;
    private List<UserResponse> residents;
}
