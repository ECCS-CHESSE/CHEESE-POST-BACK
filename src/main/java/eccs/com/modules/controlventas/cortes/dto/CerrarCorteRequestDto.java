package eccs.com.modules.controlventas.cortes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CerrarCorteRequestDto {

    @NotNull
    private Integer id_eccs_sucursal;

    @NotNull
    private Integer id_eccs_empleado;
   
    @NotNull
    private Integer id;

}
