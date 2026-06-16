package eccs.com.modules.controlventas.comprobanteventa.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgregarProductoRequestDto {
    @NotNull
    private Integer id_sucursal;
    @NotNull
    private Integer id_venta;
    @NotNull
    private Integer id_empleado;
    @NotNull
    private Integer producto;
    @NotNull
    private Integer precio;
}
