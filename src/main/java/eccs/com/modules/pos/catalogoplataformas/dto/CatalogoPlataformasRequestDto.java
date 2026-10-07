package eccs.com.modules.pos.catalogoplataformas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CatalogoPlataformasRequestDto {
    @NotNull
    private Integer id_sucursal;
}
