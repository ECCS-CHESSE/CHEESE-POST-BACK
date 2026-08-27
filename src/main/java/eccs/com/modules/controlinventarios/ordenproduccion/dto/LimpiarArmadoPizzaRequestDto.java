package eccs.com.modules.controlinventarios.ordenproduccion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LimpiarArmadoPizzaRequestDto {
    @NotNull
    private Integer id_sucursal;
    @NotNull
    private Long id_venta;
    @NotNull
    private String id_config;
}
