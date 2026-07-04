package eccs.com.modules.controlinventarios.especialidades.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "eccs_pizza_especialidades")
@Data
public class PizzaEspecialidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_eccs_producto_especialidad")
    private Integer idEccsProductoEspecialidad;

    @Column(name = "id_eccs_producto")
    private Integer idEccsProducto;

    private BigDecimal cantidad_producto = BigDecimal.ONE;
    private BigDecimal precio_producto = BigDecimal.ONE;

}
