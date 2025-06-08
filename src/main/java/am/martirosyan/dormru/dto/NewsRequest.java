package am.martirosyan.dormru.dto;

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
