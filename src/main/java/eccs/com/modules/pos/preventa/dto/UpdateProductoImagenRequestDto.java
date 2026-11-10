package eccs.com.modules.pos.preventa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProductoImagenRequestDto {
    @NotNull
    private Integer id_producto;

    @NotNull
    private Integer id_sucursal;

    @NotBlank
    private String imagen;
}
