package eccs.com.modules.controlventas.cortes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AbrirCorteRequestDto {

    @NotNull
    private Integer id_eccs_sucursal;

    @NotNull
    private Integer id_eccs_empleado;

    @NotNull
    private BigDecimal monto;
}
