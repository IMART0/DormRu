package am.martirosyan.dormru.dto.response;

import am.martirosyan.dormru.dto.request.RoleRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private String groupNumber;
    private Integer roomNumber;
    private String image;
    private Set<RoleRequest> roles;
}
