package am.martirosyan.dormru.service.api;

import am.martirosyan.dormru.dto.EventResponse;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    List<EventResponse> searchEvents(String keyword, LocalDate date);
}
