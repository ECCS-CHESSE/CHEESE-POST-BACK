package eccs.com.modules.controlinventarios.especialidades.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "eccs_pizza_especialidades")
@Data
public class EspecialidadesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer id_eccs_producto_especialidad;
    private Integer id_eccs_producto;
    private BigDecimal cantidad_producto;
    private BigDecimal precio_producto;
    private BigDecimal costo_producto;
    private Integer id_eccs_sucursal;
}
