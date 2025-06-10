package am.martirosyan.dormru.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequest {
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String location;
    private Long createdById;
}
