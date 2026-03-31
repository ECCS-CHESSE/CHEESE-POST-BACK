package eccs.com.auth.login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "eccs_empleado")
@Data
public class AuthEntity {
    @Id
    private Long id;
}
