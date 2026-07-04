package eccs.com.modules.controlinventarios.promociones.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "eccs_pizza_promociones")
@Data
public class PromocionesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer id_eccs_producto;
    private BigDecimal cantidad_producto;
    private BigDecimal precio_producto;
    private BigDecimal costo_producto;
    private Integer id_eccs_equivalencia_producto;
    private Integer id_eccs_clasificacion;
    private Integer id_eccs_sucursal;
}
