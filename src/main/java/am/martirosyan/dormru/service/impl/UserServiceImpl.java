package am.martirosyan.dormru.service.impl;

import am.martirosyan.dormru.dto.UserRequest;
import am.martirosyan.dormru.dto.UserResponse;
import am.martirosyan.dormru.exception.RoomNotExistException;
import am.martirosyan.dormru.exception.UserAlreadyExistsException;
import am.martirosyan.dormru.mapper.UserMapper;
import am.martirosyan.dormru.model.Role;
import am.martirosyan.dormru.model.User;
import am.martirosyan.dormru.repository.RoleRepository;
import am.martirosyan.dormru.repository.RoomRepository;
import am.martirosyan.dormru.repository.UserRepository;
import am.martirosyan.dormru.service.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoomRepository roomRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserRequest dto) {
        User user = userMapper.toEntity(dto);
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует");
        }

        if (roomRepository.findByRoomNumber(dto.getRoomNumber()).isEmpty()) {
            throw new RoomNotExistException("Комната с номером %d не существует".formatted(dto.getRoomNumber()));
        }

        Role role = roleRepository.findUserRole();
        user.setRoles(Set.of(role));
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        userRepository.save(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с email %s не найден".formatted(email)));
        return userMapper.toDto(user);
    }

    @Override
    public void updateProfileImage(String email, String imageUrl) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

        // Получаем старый путь к изображению
        String oldImagePath = user.getImage();

        // Удаляем старое фото с диска, если оно есть
        if (oldImagePath != null && !oldImagePath.isEmpty()) {
            try {
                Path path = Paths.get(oldImagePath);
                if (Files.exists(path)) {
                    Files.delete(path);
                }
            } catch (IOException e) {
                // Логируем ошибку, но не прерываем работу
                log.error("Не удалось удалить старое фото: {}", e.getMessage());
            }
        }

        // Обновляем путь к фото в профиле пользователя
        user.setImage(imageUrl);
        userRepository.save(user);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: %s".formatted(username)));
    }
}
