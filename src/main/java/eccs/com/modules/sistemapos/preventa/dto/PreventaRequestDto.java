package eccs.com.modules.sistemapos.preventa.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PreventaRequestDto {
    @NotNull
    private Integer id_sucursal;
}
