package eccs.com.modules.facturacion.timbrar.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TimbrarRequestDto {

    @NotBlank(message = "api_key es requerida")
    private String api_key;

    @NotBlank(message = "usuario es requerido")
    private String usuario;

    @NotBlank(message = "jsonB64 es requerido")
    private String jsonB64;
}
