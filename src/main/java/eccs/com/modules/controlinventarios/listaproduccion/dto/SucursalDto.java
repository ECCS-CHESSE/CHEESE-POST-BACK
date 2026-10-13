package eccs.com.modules.controlinventarios.listaproduccion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SucursalDto {
    @NotNull
    private Integer id_sucursal;
}
