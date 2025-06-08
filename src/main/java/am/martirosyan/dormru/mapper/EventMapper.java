package am.martirosyan.dormru.mapper;

import am.martirosyan.dormru.dto.EventRequest;
import am.martirosyan.dormru.dto.EventResponse;
import am.martirosyan.dormru.model.Event;
import am.martirosyan.dormru.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMapper implements Mapper<Event, EventRequest, EventResponse> {

    private final UserMapper userMapper;

    @Override
    public Event toEntity(EventRequest request) {
        return Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .eventDate(request.getEventDate())
                .location(request.getLocation())
                .createdBy(User.builder().id(request.getCreatedById()).build())
                .build();
    }

    @Override
    public EventResponse toDto(Event event) {
        return EventResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .location(event.getLocation())
                .createdBy(userMapper.toDto(event.getCreatedBy()))
                .build();
    }
}
