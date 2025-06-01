package am.martirosyan.dormru.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 20, message = "Пароль должен быть от 6 до 20 символов")
    private String password;

    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;

    @NotNull(message = "Возраст не может быть пустым")
    @Min(16)
    private Integer age;

    @NotBlank(message = "Номер группы не может быть пустым")
    @Size(min = 6, max = 8 , message = "Номер группы должен быть от 6 до 8 символов")
    private String groupNumber;

    @NotNull(message = "Номер комнаты не может быть пустым")
    private Integer roomNumber;
    private Set<RoleDto> roles;
}
