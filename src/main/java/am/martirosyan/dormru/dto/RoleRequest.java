package am.martirosyan.dormru.dto;

import am.martirosyan.dormru.model.Role;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleRequest {
    private Role.RoleName name;
}
