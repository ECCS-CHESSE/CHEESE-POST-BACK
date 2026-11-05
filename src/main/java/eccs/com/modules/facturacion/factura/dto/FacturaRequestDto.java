package eccs.com.modules.facturacion.factura.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FacturaRequestDto {

    @NotNull(message = "folio es requerido")
    private Integer folio;

    @NotBlank(message = "sucursal es requerida")
    private String sucursal;
}
