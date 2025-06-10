package am.martirosyan.dormru.mapper;

import am.martirosyan.dormru.dto.request.RoleRequest;
import am.martirosyan.dormru.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements Mapper<Role, RoleRequest, RoleRequest> {

    @Override
    public Role toEntity(RoleRequest roleRequest) {
        return Role.builder()
                .name(roleRequest.getName())
                .build();
    }

    @Override
    public RoleRequest toDto(Role role) {
        return RoleRequest.builder()
                .name(role.getName())
                .build();
    }
}
