package eccs.com.modules.controlinventarios.ordenproduccion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModalDataIngredientesDto {

    @NotNull private Integer id_equivalencia;
    @NotNull private Integer id_sucursal;
    @NotNull private Integer idVenta;

}
