package am.martirosyan.dormru.mapper;

import am.martirosyan.dormru.dto.RoleDto;
import am.martirosyan.dormru.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements Mapper<Role, RoleDto> {

    @Override
    public Role toEntity(RoleDto roleDto) {
        return Role.builder()
                .name(roleDto.getName())
                .build();
    }

    @Override
    public RoleDto toDto(Role role) {
        return RoleDto.builder()
                .name(role.getName())
                .build();
    }
}
