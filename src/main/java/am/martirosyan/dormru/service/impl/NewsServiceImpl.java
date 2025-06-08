package am.martirosyan.dormru.service.impl;

import am.martirosyan.dormru.dto.NewsResponse;
import am.martirosyan.dormru.exception.NewsNotFoundException;
import am.martirosyan.dormru.mapper.NewsMapper;
import am.martirosyan.dormru.repository.NewsRepository;
import am.martirosyan.dormru.service.api.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Override
    public List<NewsResponse> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::toDto)
                .toList();
    }

    @Override
    public NewsResponse getById(Long id) {
        return newsRepository.findById(id)
                .map(newsMapper::toDto)
                .orElseThrow(() -> new NewsNotFoundException("News not found with id: " + id));
    }
}
