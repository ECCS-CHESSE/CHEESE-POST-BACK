package eccs.com.modules.pos.venta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResumenArmaTuPizzaRequestDto {

    @NotNull private Integer id_sucursal;
    @NotNull private Integer id_venta;
    @NotNull private Integer id_pc;
    @NotNull private Integer id_tamano;
}