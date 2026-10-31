package eccs.com.modules.pos.preventa.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IngredientesProduccionRequestDto {
    
    @NotNull private Integer id_sucursal;
    @NotNull private Integer id_venta;
    @NotNull private Integer id_especialidad;

}
