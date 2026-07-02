package eccs.com.modules.controlinventarios.productos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateImagenProductoRequestDto {
    @NotNull
    private Long id_producto;
    @NotNull
    private byte[] imagen;
}
