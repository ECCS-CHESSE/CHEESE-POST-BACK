package eccs.com.modules.controlinventarios.especialidades.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarIngredientesRequestDto {
    @NotNull
    private Integer id_especialidad;
    @NotNull
    private int[] ids;
}
