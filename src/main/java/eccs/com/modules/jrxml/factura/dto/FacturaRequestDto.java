package eccs.com.modules.jrxml.factura.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FacturaRequestDto {

    @NotNull(message = "id_comprobante es requerido")
    private Integer id_comprobante;
}
