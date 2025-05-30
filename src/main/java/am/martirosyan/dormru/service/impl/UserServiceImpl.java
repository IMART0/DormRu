package am.martirosyan.dormru.service.impl;

import am.martirosyan.dormru.dto.UserDto;
import am.martirosyan.dormru.mapper.UserMapper;
import am.martirosyan.dormru.model.Role;
import am.martirosyan.dormru.model.User;
import am.martirosyan.dormru.repository.RoleRepository;
import am.martirosyan.dormru.repository.UserRepository;
import am.martirosyan.dormru.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public long register(UserDto dto) {
        User user = userMapper.toEntity(dto);
        Role role = roleRepository.findUserRole();
        user.setRoles(Set.of(role));
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: %s".formatted(username)));
    }
}
