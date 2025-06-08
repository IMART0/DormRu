package am.martirosyan.dormru.service.api;

import am.martirosyan.dormru.dto.UserRequest;
import am.martirosyan.dormru.dto.UserResponse;

public interface UserService {
    void register(UserRequest dto);
    UserResponse getUserByEmail(String email);

    void updateProfileImage(String username, String relativePath);
}
