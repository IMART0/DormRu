package am.martirosyan.dormru.repository;

import am.martirosyan.dormru.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByEventDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<Event> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Event> findByTitleContainingIgnoreCaseAndEventDateBetween(
            String keyword, LocalDateTime start, LocalDateTime end, Pageable pageable);
}

