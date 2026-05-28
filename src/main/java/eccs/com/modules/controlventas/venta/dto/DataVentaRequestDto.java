package eccs.com.modules.controlventas.venta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DataVentaRequestDto {

    @NotNull private Integer id_eccs_sucursal;
    @NotNull private Integer id;

}
