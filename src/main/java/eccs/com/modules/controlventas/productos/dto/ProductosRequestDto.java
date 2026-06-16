package eccs.com.modules.controlventas.productos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductosRequestDto {
    @NotNull
    private Integer id_sucursal;
    @NotNull
    private Integer id_estatus;
    @NotBlank
    private String descripcion;
}
