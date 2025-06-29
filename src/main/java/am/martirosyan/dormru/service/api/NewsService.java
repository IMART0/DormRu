package am.martirosyan.dormru.service.api;

import am.martirosyan.dormru.dto.response.NewsResponse;

import java.util.List;

public interface NewsService {
    List<NewsResponse> getAllNews();

    NewsResponse getById(Long id);
}
