package am.martirosyan.dormru.service.api;

import am.martirosyan.dormru.dto.request.UserRequest;
import am.martirosyan.dormru.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void register(UserRequest dto);

    UserResponse getUserByEmail(String email);

    void updateProfileImage(String username, String relativePath);

    Page<UserResponse> searchUsers(String keyword, String role, Pageable pageable);
}
