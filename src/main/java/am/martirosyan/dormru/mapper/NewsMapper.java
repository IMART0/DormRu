package am.martirosyan.dormru.mapper;

import am.martirosyan.dormru.dto.NewsRequest;
import am.martirosyan.dormru.dto.NewsResponse;
import am.martirosyan.dormru.model.News;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper implements Mapper<News, NewsRequest, NewsResponse> {
    @Override
    public News toEntity(NewsRequest newsRequest) {
        return News.builder()
                .title(newsRequest.getTitle())
                .description(newsRequest.getDescription())
                .imageUrl(newsRequest.getImageUrl())
                .build();
    }

    @Override
    public NewsResponse toDto(News news) {
        return NewsResponse.builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .imageUrl(news.getImageUrl())
                .build();
    }


}
