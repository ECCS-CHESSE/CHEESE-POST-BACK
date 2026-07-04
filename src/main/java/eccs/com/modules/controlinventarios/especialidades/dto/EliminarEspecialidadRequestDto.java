package eccs.com.modules.controlinventarios.especialidades.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EliminarEspecialidadRequestDto {
    @NotNull
    private Integer id_sucursal;
    @NotNull
    private Integer id;
}
