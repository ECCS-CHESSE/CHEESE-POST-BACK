package eccs.com.modules.controlinventarios.ordenproduccion.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "eccs_orden_produccion")
@Data
public class OrdenProduccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
