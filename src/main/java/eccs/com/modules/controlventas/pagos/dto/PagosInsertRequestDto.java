package eccs.com.modules.controlventas.pagos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagosInsertRequestDto {

    @NotNull
    private Integer id_sucursal;

    @NotNull
    private Integer id;

    @NotNull
    private Integer id_empleado;

    @NotNull
    private Integer id_sat_forma_pago;

    @NotNull
    private Double importe;

}
