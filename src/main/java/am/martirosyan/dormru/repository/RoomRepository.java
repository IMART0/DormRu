package am.martirosyan.dormru.repository;

import am.martirosyan.dormru.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findByRoomNumber(Integer roomNumber);

    @Query("SELECT r FROM Room r WHERE STR(r.roomNumber) LIKE %:keyword%")
    List<Room> findByRoomNumberContaining(@Param("keyword") String keyword);
}
