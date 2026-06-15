package eccs.com.modules.controlventas.directoriosucursales.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DirectorioSucursalesRequestDto {
    @NotNull
    private Integer id_sucursal;
}
