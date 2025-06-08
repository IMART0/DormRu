package am.martirosyan.dormru.service.impl;

import am.martirosyan.dormru.dto.EventResponse;
import am.martirosyan.dormru.mapper.EventMapper;
import am.martirosyan.dormru.repository.EventRepository;
import am.martirosyan.dormru.service.api.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public List<EventResponse> searchEvents(String keyword, LocalDate date) {
        Sort sortByProximity = Sort.by(Sort.Direction.DESC, "eventDate");

        if ((keyword == null || keyword.isBlank()) && date == null) {
            return eventRepository.findAll(sortByProximity)
                    .stream()
                    .map(eventMapper::toDto)
                    .toList();
        }

        if (date != null && (keyword == null || keyword.isBlank())) {
            return eventRepository.findByEventDateBetween(
                            date.atStartOfDay(),
                            date.plusDays(1).atStartOfDay(),
                            sortByProximity)
                    .stream()
                    .map(eventMapper::toDto)
                    .toList();
        }

        if (date == null) {
            return eventRepository.findByTitleContainingIgnoreCase(
                            keyword,
                            sortByProximity)
                    .stream()
                    .map(eventMapper::toDto)
                    .toList();
        }

        return eventRepository.findByTitleContainingIgnoreCaseAndEventDateBetween(
                        keyword,
                        date.atStartOfDay(),
                        date.plusDays(1).atStartOfDay(),
                        sortByProximity)
                .stream()
                .map(eventMapper::toDto)
                .toList();
    }


}
