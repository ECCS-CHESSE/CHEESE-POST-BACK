package eccs.com.modules.controlinventarios.ordenproduccion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrdenProduccionRequestDto {
    @NotNull private Integer id_sucursal;
    @NotNull private Integer id;
    @NotNull private Integer id_venta;
    @NotNull private Integer id_lado_pizza;
    @NotNull private Integer id_pc;
    @NotNull private Integer id_tamano;
}
