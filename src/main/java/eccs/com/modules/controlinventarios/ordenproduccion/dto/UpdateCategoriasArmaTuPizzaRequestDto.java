package eccs.com.modules.controlinventarios.ordenproduccion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCategoriasArmaTuPizzaRequestDto {
    @NotNull
    private Integer id_sucursal;
    @NotNull
    private Integer id_venta;
    @NotNull
    private Integer id_equivalencia;
}
