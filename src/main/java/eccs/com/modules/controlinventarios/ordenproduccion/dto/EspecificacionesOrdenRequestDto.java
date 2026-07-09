package eccs.com.modules.controlinventarios.ordenproduccion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EspecificacionesOrdenRequestDto {
    @NotNull
    private Integer id_sucursal;
    @NotNull
    private Integer id_venta;
    @NotBlank
    private String especificaciones;
}
