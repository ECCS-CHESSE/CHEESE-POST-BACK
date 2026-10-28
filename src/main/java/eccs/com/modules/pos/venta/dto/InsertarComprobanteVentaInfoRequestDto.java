package eccs.com.modules.pos.venta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InsertarComprobanteVentaInfoRequestDto {

    @NotNull private Integer id_sucursal;
    @NotNull private Integer id_venta;
    @NotNull private Integer id_pc;
    @NotNull private Integer tamano;
    @NotNull private Integer id_empleado;
}
