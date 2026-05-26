package eccs.com.modules.controlventas.comprobanteventa.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ComprobanteVentaRequestDto {
    @NotNull
    private Integer id_eccs_sucursal;
    @NotNull
    private Integer id_eccs_cliente;
    @NotNull
    private Integer id_eccs_empleado;
    @NotNull
    private Integer id_eccs_tipo_comprobante;
}
