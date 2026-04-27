package eccs.com.modules.controlempresa.catalogosucursales.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CatalogoSucursalesRequestDto {
    @NotNull(message = "id_estatus es requerido")
    private Integer id_estatus;
}
