package eccs.com.modules.controlinventarios.equivalencias.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "eccs_equivalencias")
@Data
public class EquivalenciasEntity {
    @Id
    private Long id;
}
