package eccs.com.modules.controlventas.clientes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientesAgregarDomicilioVentaRequestDto {
    @NotNull
    private Integer id_venta;
    @NotNull
    private Integer id_domicilio;
}
