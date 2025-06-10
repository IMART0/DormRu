package am.martirosyan.dormru.service.api;

import am.martirosyan.dormru.dto.request.UserRequest;
import am.martirosyan.dormru.dto.response.UserResponse;

public interface UserService {
    void register(UserRequest dto);
    UserResponse getUserByEmail(String email);

    void updateProfileImage(String username, String relativePath);
}
