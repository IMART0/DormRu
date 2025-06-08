package am.martirosyan.dormru.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse {
    Long id;
    String title;
    String description;
    String imageUrl;
}
