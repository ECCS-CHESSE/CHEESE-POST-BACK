package eccs.com.modules.controlventas.catalogoclientes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CatalogoClientesRequestDto {
    @NotNull(message = "id_estatus es requerido")
    private Integer id_estatus;
}
