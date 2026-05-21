package eccs.com.modules.controlventas.comprobanteventa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT 1 AS id")
@Data
public class ComprobanteVentaEntity {
    @Id
    private Long id;
}
