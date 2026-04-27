package eccs.com.modules.controlempresa.sucursal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateSucursalRequestDto {
    @NotNull(message = "id_sucursal es requerido")
    private Integer id_sucursal;
    @NotBlank(message = "sucursal es requerido")
    private String sucursal;
}
