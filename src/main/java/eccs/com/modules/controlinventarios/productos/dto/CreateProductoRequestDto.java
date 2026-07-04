package eccs.com.modules.controlinventarios.productos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProductoRequestDto {
    @NotBlank
    private String descripcion;
    @NotNull
    private Integer id_sucursal;
    @NotNull
    private Integer id_clasificacion;
}
