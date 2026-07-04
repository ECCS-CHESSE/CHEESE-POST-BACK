package eccs.com.modules.controlinventarios.listaproduccion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ListaTipoSalsaRequestDto {
    @NotNull
    private Integer id_sucursal;
}
