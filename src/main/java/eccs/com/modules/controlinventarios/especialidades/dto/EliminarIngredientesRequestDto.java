package eccs.com.modules.controlinventarios.especialidades.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EliminarIngredientesRequestDto {
    @NotNull
    private int[] ids;
}
