package am.martirosyan.dormru.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequest {
    String title;
    String description;
    String imageUrl;
}
