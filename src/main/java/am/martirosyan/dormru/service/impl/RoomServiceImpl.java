package am.martirosyan.dormru.service.impl;

import am.martirosyan.dormru.dto.response.RoomResponse;
import am.martirosyan.dormru.dto.response.RoomResponseWithResidents;
import am.martirosyan.dormru.mapper.RoomMapper;
import am.martirosyan.dormru.mapper.UserMapper;
import am.martirosyan.dormru.repository.RoomRepository;
import am.martirosyan.dormru.repository.UserRepository;
import am.martirosyan.dormru.service.api.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    private final RoomMapper roomMapper;
    private final UserMapper userMapper;

    @Override
    public List<RoomResponse> searchRooms(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return roomRepository.findAll().stream()
                    .map(roomMapper::toDto)
                    .toList();
        } else {
            return roomRepository.findByRoomNumberContaining(keyword).stream()
                    .map(roomMapper::toDto)
                    .toList();
        }
    }

    @Override
    public RoomResponseWithResidents getRoomWithResidents(Integer roomId) {
        RoomResponseWithResidents roomResponseWithoutResidents = roomRepository.findById(roomId)
                .map(roomMapper::toDtoWithResigents)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));

        roomResponseWithoutResidents.setResidents(
                userRepository.findByRoomNumber(roomResponseWithoutResidents.getRoomNumber()).stream()
                        .map(userMapper::toDto)
                        .toList()
        );

        return roomResponseWithoutResidents;
    }
}
