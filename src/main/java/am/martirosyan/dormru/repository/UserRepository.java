package am.martirosyan.dormru.repository;

import am.martirosyan.dormru.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = """
        SELECT EXISTS (
            SELECT 1
            FROM event_registrations
            WHERE user_id = :userId AND event_id = :eventId
        )
        """, nativeQuery = true)
    boolean isRegisteredForEvent(Long userId, Long eventId);

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO event_registrations (user_id, event_id, registered_at)
        VALUES (:userId, :eventId, now())
        ON CONFLICT DO NOTHING
        """, nativeQuery = true)
    void registerToEvent(Long userId, Long eventId);
}

