package eccs.com.modules.controlinventarios.ordenproduccion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LimpiarIngredientesRequestDto {
    @NotNull
    private Integer id_sucursal;
    @NotNull
    private Integer id_especialidad1;
    @NotNull
    private Integer id_especialidad2;
    @NotNull
    private Integer id_venta;
}
