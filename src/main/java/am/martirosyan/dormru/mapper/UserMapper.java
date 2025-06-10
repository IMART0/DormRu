package am.martirosyan.dormru.mapper;

import am.martirosyan.dormru.dto.request.UserRequest;
import am.martirosyan.dormru.dto.response.UserResponse;
import am.martirosyan.dormru.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserRequest, UserResponse> {

    private final RoleMapper roleMapper;

    @Override
    public User toEntity(UserRequest userRequest) {
        return User.builder()
                .email(userRequest.getEmail())
                .passwordHash(userRequest.getPassword())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .age(userRequest.getAge())
                .groupNumber(userRequest.getGroupNumber())
                .image(userRequest.getImage())
                .roomNumber(userRequest.getRoomNumber())
                .build();
    }

    @Override
    public UserResponse toDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .groupNumber(user.getGroupNumber())
                .roomNumber(user.getRoomNumber())
                .image(user.getImage())
                .roles(user.getRoles().stream().map(
                        roleMapper::toDto
                ).collect(Collectors.toSet()))
                .build();
    }
}
