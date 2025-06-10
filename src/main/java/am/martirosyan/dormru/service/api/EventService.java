package am.martirosyan.dormru.service.api;

import am.martirosyan.dormru.dto.response.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface EventService {

    Page<EventResponse> searchEvents(String keyword, LocalDate date, Pageable pageable);

    EventResponse getById(Long id);

    boolean isUserAlreadyRegistered(Long userId, Long eventId);

    void registerUserForEvent(Long userId, Long eventId);
}
