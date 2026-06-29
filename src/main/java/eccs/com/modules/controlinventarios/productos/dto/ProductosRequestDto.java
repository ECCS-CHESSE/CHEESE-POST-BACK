package eccs.com.modules.controlinventarios.productos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductosRequestDto {
    @NotNull(message = "id_sucursal es requerido")
    private Integer id_sucursal;
    @NotNull(message = "id_clasificacion es requerido")
    private Integer id_clasificacion;
}
