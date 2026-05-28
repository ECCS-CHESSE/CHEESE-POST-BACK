package eccs.com.modules.controlventas.clientes.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT 1 AS id")
@Data
public class ClientesEntity {
    @Id
    private Long id;
}
