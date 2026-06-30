package eccs.com.modules.controlventas.comprobanteventa.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemAumentaRequestDto {
    @NotNull
    private Integer id_venta;
    @NotNull
    private Integer id_cantidad;
}
