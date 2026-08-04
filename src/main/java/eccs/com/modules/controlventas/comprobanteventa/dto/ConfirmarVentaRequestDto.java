package eccs.com.modules.controlventas.comprobanteventa.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConfirmarVentaRequestDto {
    @NotNull
    private Integer id_sucursal;
    @NotNull
    private Integer id;
    @NotNull
    private Integer id_empleado;
}
