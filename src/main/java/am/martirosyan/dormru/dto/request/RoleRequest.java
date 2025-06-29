package am.martirosyan.dormru.dto.request;

import am.martirosyan.dormru.model.Role;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleRequest {
    private Role.RoleName name;

    @Override
    public String toString() {
        return name.toRuString();
    }
}
