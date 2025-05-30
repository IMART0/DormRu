package am.martirosyan.dormru.mapper;

import am.martirosyan.dormru.dto.RoleDto;
import am.martirosyan.dormru.dto.UserDto;
import am.martirosyan.dormru.model.Role;
import am.martirosyan.dormru.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {

    private final RoleMapper roleMapper;

    @Override
    public User toEntity(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .passwordHash(userDto.getPassword())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .age(userDto.getAge())
                .groupNumber(userDto.getGroupNumber())
                .roomNumber(userDto.getRoomNumber())
                .build();
    }

    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .groupNumber(user.getGroupNumber())
                .roomNumber(user.getRoomNumber())
                .roles(user.getRoles().stream().map(
                        roleMapper::toDto
                ).collect(Collectors.toSet()))
                .build();
    }
}
