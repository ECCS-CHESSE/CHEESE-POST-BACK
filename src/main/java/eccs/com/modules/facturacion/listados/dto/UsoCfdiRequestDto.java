package eccs.com.modules.facturacion.listados.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsoCfdiRequestDto {

    @NotBlank(message = "codigo es requerido")
    private String codigo;
}
