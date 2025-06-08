package am.martirosyan.dormru.service.impl;

import am.martirosyan.dormru.dto.EventResponse;
import am.martirosyan.dormru.exception.EventNotFoundException;
import am.martirosyan.dormru.mapper.EventMapper;
import am.martirosyan.dormru.repository.EventRepository;
import am.martirosyan.dormru.service.api.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public Page<EventResponse> searchEvents(String keyword, LocalDate date, Pageable pageable) {
        if ((keyword == null || keyword.isBlank()) && date == null) {
            return eventRepository.findAll(pageable).map(eventMapper::toDto);
        }

        if (date != null && (keyword == null || keyword.isBlank())) {
            return eventRepository.findByEventDateBetween(
                    date.atStartOfDay(), date.plusDays(1).atStartOfDay(), pageable
            ).map(eventMapper::toDto);
        }

        if (date == null) {
            return eventRepository.findByTitleContainingIgnoreCase(keyword, pageable)
                    .map(eventMapper::toDto);
        }

        return eventRepository.findByTitleContainingIgnoreCaseAndEventDateBetween(
                keyword, date.atStartOfDay(), date.plusDays(1).atStartOfDay(), pageable
        ).map(eventMapper::toDto);
    }

    @Override
    public EventResponse getById(Long id) {
        return eventRepository.findById(id)
                .map(eventMapper::toDto)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));
    }


}
