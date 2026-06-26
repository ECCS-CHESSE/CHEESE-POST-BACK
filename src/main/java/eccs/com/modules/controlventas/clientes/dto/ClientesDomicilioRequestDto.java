package eccs.com.modules.controlventas.clientes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientesDomicilioRequestDto {
    @NotNull(message = "id_eccs_cliente es requerido")
    private Integer id_eccs_cliente;
}
