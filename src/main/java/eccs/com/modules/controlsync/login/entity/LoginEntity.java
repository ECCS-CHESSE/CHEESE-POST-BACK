package eccs.com.modules.controlsync.login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "eccs_empleado", schema = "controlsync")
@Data
public class LoginEntity {
    @Id
    private Long id;
}
