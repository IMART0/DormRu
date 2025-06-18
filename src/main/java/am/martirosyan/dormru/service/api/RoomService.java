package am.martirosyan.dormru.service.api;

import am.martirosyan.dormru.dto.response.RoomResponse;
import am.martirosyan.dormru.dto.response.RoomResponseWithResidents;

import java.util.List;

public interface RoomService {
    List<RoomResponse> searchRooms(String keyword);

    RoomResponseWithResidents getRoomWithResidents(Integer roomId);
}
