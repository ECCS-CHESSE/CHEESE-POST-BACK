package eccs.com.modules.controlinventarios.ordenproduccion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EliminarIngredientesIzquierdaDerecharRequestDto {
    @NotNull private Integer id_sucursal;
    @NotNull private Integer id_venta;
    @NotNull private Integer id_especialidad;
    @NotNull private Integer id_lado;
    @NotNull private Integer id_pc;
}
