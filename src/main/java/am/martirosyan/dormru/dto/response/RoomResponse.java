package am.martirosyan.dormru.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponse {
    private Integer roomNumber;
    private Integer capacity;
}
