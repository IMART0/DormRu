package am.martirosyan.dormru.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public static RoleName toRoleName(String role) {
        if (role.equals("USER")) {
            return RoleName.ROLE_USER;
        } else if (role.equals("ADMIN")) {
            return RoleName.ROLE_ADMIN;
        }
        return RoleName.ROLE_USER;
    }


    @Override
    public String getAuthority() {
        return name.toString();
    }

    public enum RoleName {
        ROLE_USER,
        ROLE_ADMIN;

        public String toRuString() {
            return switch (this) {
                case ROLE_USER -> "Пользователь";
                case ROLE_ADMIN -> "Администратор";
            };
        }
    }
}
