package eccs.com.modules.controlinventarios.ordenproduccion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SucursalRequestDto {
    @NotNull private Integer id_sucursal;
}
