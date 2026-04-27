package eccs.com.modules.controlempresa.sucursal.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT 1 AS id")
@Data
public class SucursalEntity {
    @Id
    private Long id;
}
