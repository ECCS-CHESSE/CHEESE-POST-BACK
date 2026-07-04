package eccs.com.modules.controlinventarios.especialidades.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ActualizarEspecialidadRequestDto {
    @NotNull
    private Long id;
    @NotNull
    private Integer id_eccs_producto;
    @NotNull
    private BigDecimal cantidad_producto;
    @NotNull
    private BigDecimal precio_producto;
    @NotNull
    private BigDecimal costo_producto;
    @NotNull
    private Integer id_eccs_equivalencia_producto;
    @NotNull
    private Integer id_eccs_clasificacion;
    @NotNull
    private Integer id_eccs_sucursal;
}
