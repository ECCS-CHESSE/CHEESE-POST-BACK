package eccs.com.modules.controlempresa.sucursal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateSucursalRequestDto {
    @NotBlank(message = "sucursal es requerido")
    private String sucursal;
}
