package eccs.com.modules.controlinventarios.listaproduccion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ListaTipoEquivalenciaRequestDto {
    @NotNull
    private Integer id_sucursal;
    @NotNull
    private Integer id_equivalencia;
}
