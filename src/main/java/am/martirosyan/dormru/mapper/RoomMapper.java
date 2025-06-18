package am.martirosyan.dormru.mapper;

import am.martirosyan.dormru.dto.request.RoomRequest;
import am.martirosyan.dormru.dto.response.RoomResponse;
import am.martirosyan.dormru.dto.response.RoomResponseWithResidents;
import am.martirosyan.dormru.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper implements Mapper<Room, RoomRequest, RoomResponse> {
    @Override
    public Room toEntity(RoomRequest roomRequest) {
        return Room.builder()
                .roomNumber(roomRequest.getRoomNumber())
                .build();
    }

    @Override
    public RoomResponse toDto(Room room) {
        return RoomResponse.builder()
                .roomNumber(room.getRoomNumber())
                .capacity(room.getCapacity())
                .build();
    }

    public RoomResponseWithResidents toDtoWithResigents(Room room) {
        return RoomResponseWithResidents.builder()
                .roomNumber(room.getRoomNumber())
                .capacity(room.getCapacity())
                .build();
    }
}
