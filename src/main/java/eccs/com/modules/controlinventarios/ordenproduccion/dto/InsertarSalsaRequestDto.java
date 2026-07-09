package eccs.com.modules.controlinventarios.ordenproduccion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InsertarSalsaRequestDto {
    @NotNull
    private Integer id_sucursal;
    @NotNull
    private Integer id_producto;
    @NotNull
    private Integer id_venta;
}
