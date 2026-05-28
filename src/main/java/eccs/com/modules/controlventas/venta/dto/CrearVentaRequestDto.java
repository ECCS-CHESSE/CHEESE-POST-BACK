package eccs.com.modules.controlventas.venta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CrearVentaRequestDto {

    @NotNull private Integer id_eccs_sucursal;
    @NotNull private Integer id_eccs_cliente;
    @NotNull private Integer id_eccs_empleado;
    @NotNull private Integer id_eccs_tipo_pedido;


}
