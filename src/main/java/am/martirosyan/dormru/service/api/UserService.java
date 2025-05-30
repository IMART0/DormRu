package am.martirosyan.dormru.service.api;

import am.martirosyan.dormru.dto.UserDto;

public interface UserService {
    long register(UserDto dto);
}
