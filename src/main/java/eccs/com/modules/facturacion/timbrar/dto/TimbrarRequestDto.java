package eccs.com.modules.facturacion.timbrar.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TimbrarRequestDto {

    @NotNull(message = "id_comprobante_venta es requerido")
    private Long id_comprobante_venta;

}
