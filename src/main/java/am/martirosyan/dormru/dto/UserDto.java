package am.martirosyan.dormru.dto;

import am.martirosyan.dormru.model.User;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
    private String groupNumber;
    private Integer roomNumber;
    private Set<RoleDto> roles;
}
