package am.martirosyan.dormru.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

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


    @Override
    public String getAuthority() {
        return name.toString();
    }

    public enum RoleName {
        ROLE_USER,
        ROLE_ADMIN;
    }
}
