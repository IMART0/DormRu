package am.martirosyan.dormru.repository;

import am.martirosyan.dormru.model.Event;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByEventDateBetween(LocalDateTime start, LocalDateTime end, Sort sort);

    List<Event> findByTitleContainingIgnoreCase(String title, Sort sort);

    List<Event> findByTitleContainingIgnoreCaseAndEventDateBetween(
            String title, LocalDateTime start, LocalDateTime end, Sort sort);

}

