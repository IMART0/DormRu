package am.martirosyan.dormru.repository;

import am.martirosyan.dormru.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findByRoomNumber(Integer roomNumber);
}
