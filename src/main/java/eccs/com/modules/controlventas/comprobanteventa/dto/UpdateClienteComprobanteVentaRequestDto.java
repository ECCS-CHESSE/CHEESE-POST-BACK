package eccs.com.modules.controlventas.comprobanteventa.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateClienteComprobanteVentaRequestDto {
    @NotNull
    private Integer id_venta;
    @NotNull
    private Integer id_cliente;
}
